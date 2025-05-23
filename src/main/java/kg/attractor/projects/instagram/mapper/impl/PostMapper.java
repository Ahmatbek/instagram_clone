package kg.attractor.projects.instagram.mapper.impl;

import kg.attractor.projects.instagram.dto.PostDto;
import kg.attractor.projects.instagram.dto.UserDto;
import kg.attractor.projects.instagram.mapper.Mapper;
import kg.attractor.projects.instagram.model.Post;
import kg.attractor.projects.instagram.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostMapper implements Mapper<PostDto, Post> {

    @Override
    public PostDto mapToDto(Post entity) {
        return PostDto.builder()
                .id(entity.getId())
                .userDto(UserDto.builder()
                        .id(entity.getUser().getId())
                        .username(entity.getUser().getUsername())
                        .login(entity.getUser().getLogin())
                        .info(entity.getUser().getInfo())
                        .password(entity.getUser().getPassword())
                        .avatar(entity.getUser().getAvatar())
                        .build())
                .description(entity.getDescription())
                .imageUrl(entity.getPhoto())
                .build();
    }

    @Override
    public Post mapToEntity(PostDto dto) {
        Post post = new Post();
        post.setId(dto.getId());

        User user = new User();
        user.setId(dto.getUserDto().getId());

        post.setUser(user);
        post.setDescription(dto.getDescription());
        post.setPhoto(dto.getImageUrl());
        return post;
    }
}
