package kg.attractor.projects.instagram.service.impl;

import kg.attractor.projects.instagram.dto.PostDto;
import kg.attractor.projects.instagram.mapper.impl.PostMapper;
import kg.attractor.projects.instagram.model.Post;
import kg.attractor.projects.instagram.repository.PostRepository;
import kg.attractor.projects.instagram.service.AuthorizedUserService;
import kg.attractor.projects.instagram.service.LikeService;
import kg.attractor.projects.instagram.service.PostService;
import kg.attractor.projects.instagram.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostMapper postMapper;
    private final AuthorizedUserService authorizedUserService;
    private final PostRepository postRepository;
    private final LikeService likeService;

    @Transactional
    @Override
    public PostDto savePost(PostDto postDto) {
        postDto.setUserId(authorizedUserService.getAuthorizedUser().getId());
        String fileName = Util.uploadResource(postDto.getPhoto());
        Post post = postMapper.mapToEntity(postDto);
        post.setPhoto(fileName);
        return postMapper.mapToDto(postRepository.save(post));
    }

    @Override
    public void deletePost(Long postId) {
        Long userId = authorizedUserService.getAuthorizedUser().getId();

        Post post = postRepository.findByPostIdAndUserId(userId, postId)
                .orElseThrow(() -> new NoSuchElementException("Post not found" + postId));
        postRepository.delete(post);

    }

    @Override
    public List<PostDto> getAllPosts() {
        List<PostDto> posts = postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .toList();

        posts.forEach(postDto -> postDto.setLikedByCurrentUser(
                        likeService.isLikeExist(postDto.getId(), authorizedUserService.getAuthorizedUserId())
                )
        );

        return posts;
    }

    @Override
    public List<PostDto> getUsersPosts(long userId) {
        List<PostDto> posts = postRepository.findUsersPosts(userId)
                .stream()
                .map(postMapper::mapToDto)
                .toList();

        posts.forEach(postDto -> {
            postDto.setLikesCount(likeService.findAllLikesByPostId(postDto.getId()));
            postDto.setLikedByCurrentUser(likeService.isLikeExist(postDto.getId(), userId));
        });
        return posts;
    }

    @Override
    public PostDto findPostById(Long postId) {
        Assert.notNull(postId, "post id cannot be null");
        PostDto postDto = postRepository.findById(postId)
                .map(postMapper::mapToDto)
                .orElseThrow(() -> new NoSuchElementException("Post not found" + postId));

        postDto.setLikesCount(likeService.findAllLikesByPostId(postId));
        postDto.setLikedByCurrentUser(likeService.isLikeExist(postDto.getId(), authorizedUserService.getAuthorizedUserId()));
        return postDto;
    }

    @Override
    public void makeSurePostExist(Long postId) {
        if (!postRepository.existsById(postId)) throw new NoSuchElementException("post not found " + postId);
    }

    @Transactional
    @Override
    public PostDto updatePostDto(PostDto postDto) throws IOException {
        Assert.notNull(postDto.getId(), "post id cannot be null");

        Post post = postRepository.findById(postDto.getId())
                .orElseThrow(() -> new NoSuchElementException("post not found by id " + postDto.getId()));

        post.setDescription(postDto.getDescription());
        if (postDto.getPhoto() != null && !postDto.getPhoto().isEmpty())
            post.setPhoto(Util.uploadResource(postDto.getPhoto()));
        return postMapper.mapToDto(postRepository.save(post));
    }
}
