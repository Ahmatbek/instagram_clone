package kg.attractor.projects.instagram.mapper.impl;

import kg.attractor.projects.instagram.dto.LikeDto;
import kg.attractor.projects.instagram.mapper.Mapper;
import kg.attractor.projects.instagram.model.Like;
import kg.attractor.projects.instagram.model.Post;
import kg.attractor.projects.instagram.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeMapper implements Mapper<LikeDto, Like> {

    @Override
    public LikeDto mapToDto(Like entity) {
        return LikeDto.builder()
                .id(entity.getId())
                .postId(entity.getPost().getId())
                .userId(entity.getUser().getId())
                .build();
    }

    @Override
    public Like mapToEntity(LikeDto dto) {
        Post post = new Post();
        post.setId(dto.getPostId());

        User user = new User();
        user.setId(dto.getUserId());

        Like like = new Like();
        like.setId(dto.getId());
        like.setPost(post);
        like.setUser(user);
        return like;
    }
}
