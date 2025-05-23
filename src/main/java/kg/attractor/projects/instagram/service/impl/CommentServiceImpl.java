package kg.attractor.projects.instagram.service.impl;

import kg.attractor.projects.instagram.dto.CommentDto;
import kg.attractor.projects.instagram.dto.PageHolder;
import kg.attractor.projects.instagram.dto.UserDto;
import kg.attractor.projects.instagram.mapper.impl.CommentMapper;
import kg.attractor.projects.instagram.mapper.impl.PageHolderWrapper;
import kg.attractor.projects.instagram.model.Comment;
import kg.attractor.projects.instagram.repository.CommentRepository;
import kg.attractor.projects.instagram.service.AuthorizedUserService;
import kg.attractor.projects.instagram.service.CommentService;
import kg.attractor.projects.instagram.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;
    private final CommentMapper commentMapper;
    private final AuthorizedUserService authorizedUserService;
    private final PageHolderWrapper pageHolderWrapper;

    @Transactional
    @Override
    public void save(CommentDto commentDto) {
        postService.makeSurePostExist(commentDto.getPostId());

        commentDto.setUserDto(UserDto.builder()
                .id(authorizedUserService.getAuthorizedUserId())
                .build());
        Comment comment = commentMapper.mapToEntity(commentDto);
        comment.setCreatedAt((ZonedDateTime.now(ZoneId.of("Asia/Bishkek")).toLocalDateTime()));
        commentRepository.save(comment);
    }

    @Transactional
    @Override
    public void deleteCommentById(Long commentId, Long postId) {
        Assert.notNull(commentId, "commentId must not be null");

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NoSuchElementException("comment not found"));

        Long authorizedUserId = authorizedUserService.getAuthorizedUserId();

        if (Objects.equals(authorizedUserId, comment.getPost().getUser().getId()) || Objects.equals(authorizedUserId, comment.getUser().getId())){
            commentRepository.delete(comment);
         }
        }

    @Override
    public PageHolder<CommentDto> findAllCommentByPostId(Long postId, int page, int size) {
        Assert.notNull(postId, "postId must not be null");

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").ascending());
        Page<CommentDto> comments = commentRepository.findAllCommentsByPostId(postId, pageable)
                .map(commentMapper::mapToDto);

        return pageHolderWrapper.wrapPageHolder(comments);
    }
}
