package kg.attractor.projects.instagram.service;

import kg.attractor.projects.instagram.dto.PageHolder;
import kg.attractor.projects.instagram.dto.PostDto;

import java.io.IOException;
import java.util.List;

public interface PostService {
    PostDto savePost(PostDto postDto) throws IOException;

    void deletePost(Long postId);

    PageHolder<PostDto> getAllPosts(int page, int size);

    List<PostDto> getUsersPosts(long userId);

    PostDto findPostById(Long postId);

    void makeSurePostExist(Long postId);

    PostDto updatePostDto(PostDto postDto) throws IOException;

    PageHolder<PostDto> findUserLikedPosts(int page, int size);
}
