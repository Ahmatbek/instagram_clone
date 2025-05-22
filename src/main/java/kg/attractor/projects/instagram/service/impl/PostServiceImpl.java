package kg.attractor.projects.instagram.service.impl;

import kg.attractor.projects.instagram.dto.PostDto;
import kg.attractor.projects.instagram.mapper.impl.PostMapper;
import kg.attractor.projects.instagram.model.Post;
import kg.attractor.projects.instagram.repository.PostRepository;
import kg.attractor.projects.instagram.service.AuthorizedUserService;
import kg.attractor.projects.instagram.service.PostService;
import kg.attractor.projects.instagram.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    @Override
    public PostDto savePost(PostDto postDto) throws IOException {
        postDto.setUserId(authorizedUserService.getAuthorizedUser().getId());
        String fileName = Util.uploadResource(postDto.getPhoto());
        Post post = postMapper.mapToEntity(postDto);
        post.setPhoto(fileName);
        return postMapper.mapToDto(postRepository.save(post));
    }

    @Override
    public void deletePost(Long postId) {
        Long userId = authorizedUserService.getAuthorizedUser().getId();

        postRepository.findByPostIdAndUserId(userId,postId)
                .orElseThrow(()-> new NoSuchElementException("Post not found"+postId));
        postRepository.deleteById(postId);

    }

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .toList();
    }

    @Override
    public List<PostDto> getUsersPosts(long userId) {
        return postRepository.findUsersPosts(userId)
                .stream()
                .map(postMapper::mapToDto)
                .toList();
    }

    @Override
    public PostDto findPostById(Long postId) {
        Assert.notNull(postId, "post id cannot be null");
        return postRepository.findById(postId)
                .map(postMapper::mapToDto)
                .orElseThrow(() -> new NoSuchElementException("Post not found" + postId));
    }

    @Override
    public PostDto updatePostDto(PostDto postDto) throws IOException {
        Assert.notNull(postDto.getId(), "post id cannot be null");

        Post post = postRepository.findById(postDto.getId())
                .orElseThrow(() -> new NoSuchElementException("post not found by id " + postDto.getId()));

        post.setDescription(postDto.getDescription());
        if (post.getPhoto() != null) post.setPhoto(Util.uploadResource(postDto.getPhoto()));
        return postMapper.mapToDto(postRepository.save(post));
    }
}
