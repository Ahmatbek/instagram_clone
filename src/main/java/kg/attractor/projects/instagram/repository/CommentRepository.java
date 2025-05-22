package kg.attractor.projects.instagram.repository;

import kg.attractor.projects.instagram.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllCommentsByPostId(Long postId, Pageable pageable);
}
