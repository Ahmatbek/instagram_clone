package kg.attractor.projects.instagram.service;

import kg.attractor.projects.instagram.dto.CommentDto;
import kg.attractor.projects.instagram.dto.PageHolder;

public interface CommentService {
    void save(CommentDto commentDto);

    void deleteCommentById(Long commentId);

    PageHolder<CommentDto> findAllCommentByPostId(Long postId, int page, int szie);
}
