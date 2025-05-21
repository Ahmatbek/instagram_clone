package kg.attractor.projects.instagram.mapper.impl;

import kg.attractor.projects.instagram.dto.PostDto;
import kg.attractor.projects.instagram.mapper.Mapper;
import kg.attractor.projects.instagram.model.Post;
import kg.attractor.projects.instagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostMapper implements Mapper<PostDto, Post> {
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public PostDto mapToDto(Post entity) {
        return PostDto.builder()
                .id(entity.getId())
                .userLogin(entity.getUser().getLogin())
                .description(entity.getDescription())
                .build();
    }

    @Override
    public Post mapToEntity(PostDto dto) {
        Post post = new Post();
        post.setId(dto.getId());
        post.setUser(userMapper.mapToEntity(userService.findUserByLogin(dto.getUserLogin())));
        post.setDescription(dto.getDescription());
        return post;
    }
}
