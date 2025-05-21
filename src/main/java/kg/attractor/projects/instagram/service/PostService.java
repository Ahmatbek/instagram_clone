package kg.attractor.projects.instagram.service;

import kg.attractor.projects.instagram.dto.PostDto;

import java.io.IOException;

public interface PostService {
    PostDto savePost(PostDto postDto) throws IOException;
}
