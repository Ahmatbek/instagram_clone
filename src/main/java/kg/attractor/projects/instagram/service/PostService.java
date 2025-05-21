package kg.attractor.projects.instagram.service;

import kg.attractor.projects.instagram.dto.PostDto;

import java.io.IOException;
import java.util.List;

public interface PostService {
    PostDto savePost(PostDto postDto) throws IOException;
    void deletePost(Long postId);
    List<PostDto> getAllPosts();
    List<PostDto> getUsersPosts(long userId);
}
