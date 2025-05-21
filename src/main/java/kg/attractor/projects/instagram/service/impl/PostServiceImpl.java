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

import java.io.IOException;

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
}
