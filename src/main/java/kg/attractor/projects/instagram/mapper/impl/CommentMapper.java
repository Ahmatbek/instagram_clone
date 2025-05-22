package kg.attractor.projects.instagram.mapper.impl;

import kg.attractor.projects.instagram.dto.CommentDto;
import kg.attractor.projects.instagram.mapper.Mapper;
import kg.attractor.projects.instagram.model.Comment;
import kg.attractor.projects.instagram.model.Post;
import kg.attractor.projects.instagram.model.User;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class CommentMapper implements Mapper<CommentDto, Comment> {

    private final UserMapper userMapper;

    public CommentMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public CommentDto mapToDto(Comment entity) {
        return CommentDto.builder()
                .id(entity.getId())
                .userDto(userMapper.mapToDto(entity.getUser()))
                .postId(entity.getPost().getId())
                .message(entity.getMessage())
                .createdAt(entity.getCreatedAt().format(DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm:ss")))
                .build();
    }

    @Override
    public Comment mapToEntity(CommentDto dto) {
        Comment comment = new Comment();

        Post post = new Post();
        post.setId(dto.getPostId());

        User user = new User();
        user.setId(dto.getUserDto().getId());

        comment.setId(dto.getId());
        comment.setPost(post);
        comment.setUser(user);
        comment.setMessage(dto.getMessage());
        return comment;
    }
}
