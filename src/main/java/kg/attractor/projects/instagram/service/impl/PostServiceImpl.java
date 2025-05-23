package kg.attractor.projects.instagram.service.impl;

import kg.attractor.projects.instagram.dto.PageHolder;
import kg.attractor.projects.instagram.dto.PostDto;
import kg.attractor.projects.instagram.dto.UserDto;
import kg.attractor.projects.instagram.mapper.impl.PageHolderWrapper;
import kg.attractor.projects.instagram.mapper.impl.PostMapper;
import kg.attractor.projects.instagram.model.Post;
import kg.attractor.projects.instagram.repository.PostRepository;
import kg.attractor.projects.instagram.service.AuthorizedUserService;
import kg.attractor.projects.instagram.service.FollowerService;
import kg.attractor.projects.instagram.service.LikeService;
import kg.attractor.projects.instagram.service.PostService;
import kg.attractor.projects.instagram.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostMapper postMapper;
    private final AuthorizedUserService authorizedUserService;
    private final PostRepository postRepository;
    private final LikeService likeService;
    private final FollowerService followerService;
    private final PageHolderWrapper pageHolderWrapper;

    @Transactional
    @Override
    public PostDto savePost(PostDto postDto) {
        postDto.setUserDto(UserDto.builder()
                .id(authorizedUserService.getAuthorizedUserId())
                .build());

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
    public PageHolder<PostDto> getAllPosts(int page, int size) {
        List<Long> userFollowers = followerService.userFollowers(authorizedUserService.getAuthorizedUserId())
                .stream()
                .map(UserDto::getId)
                .toList();

        Pageable pageable = PageRequest.of(page, size);
        long totalPostsCount = postRepository.count();
        List<PostDto> posts;

        if (userFollowers.isEmpty()) {
            posts = findAllPostsOrderedByLike(pageable);

        } else {
            posts = postRepository.findUsersPostsByUsersIds(userFollowers, pageable)
                    .stream()
                    .map(postMapper::mapToDto)
                    .collect(Collectors.toList());

            if (posts.isEmpty())
                posts = findAllPostsOrderedByLike(pageable);

            else if (posts.size() < size) {
                List<PostDto> popularPosts = findAllPostsOrderedByLike(pageable);

                int index = 0;
                for (int i = posts.size(); i < size; i++) {
                    posts.add(popularPosts.get(index));
                    index++;
                }
            }
        }
        posts.forEach(postDto -> postDto.setLikedByCurrentUser(
                        likeService.isLikeExist(postDto.getId(), authorizedUserService.getAuthorizedUserId())
                )
        );
        posts.forEach(postDto -> postDto.setLikesCount(likeService.findAllLikesByPostId(postDto.getId())));
        return pageHolderWrapper.wrapPageHolder(new PageImpl<>(posts, pageable, totalPostsCount));
    }

    private List<PostDto> findAllPostsOrderedByLike(Pageable pageable) {
        return postRepository.findAllPostsOrderedByIdLike(pageable)
                .stream()
                .map(postMapper::mapToDto)
                .toList();
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
    public PostDto updatePostDto(PostDto postDto) {
        Assert.notNull(postDto.getId(), "post id cannot be null");

        Post post = postRepository.findById(postDto.getId())
                .orElseThrow(() -> new NoSuchElementException("post not found by id " + postDto.getId()));

        post.setDescription(postDto.getDescription());
        if (postDto.getPhoto() != null && !postDto.getPhoto().isEmpty())
            post.setPhoto(Util.uploadResource(postDto.getPhoto()));
        return postMapper.mapToDto(postRepository.save(post));
    }

    @Override
    public PageHolder<PostDto> findUserLikedPosts(int page, int size) {
        Page<PostDto> posts = postRepository.findUserPosts(authorizedUserService.getAuthorizedUserId(), PageRequest.of(page, size))
                .map(postMapper::mapToDto);

        return pageHolderWrapper.wrapPageHolder(posts);
    }
}
