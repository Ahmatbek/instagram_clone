package kg.attractor.projects.instagram.service.impl;

import kg.attractor.projects.instagram.dto.LikeDto;
import kg.attractor.projects.instagram.exceptions.LikeAlreadyExistException;
import kg.attractor.projects.instagram.mapper.impl.LikeMapper;
import kg.attractor.projects.instagram.model.Like;
import kg.attractor.projects.instagram.repository.LikeRepository;
import kg.attractor.projects.instagram.service.AuthorizedUserService;
import kg.attractor.projects.instagram.service.LikeService;
import kg.attractor.projects.instagram.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;
    private PostService postService;
    private final LikeMapper likeMapper;
    private final AuthorizedUserService authorizedUserService;

    @Autowired
    public void setPostService(@Lazy PostService postService) {
        this.postService = postService;
    }

    @Override
    public long findAllLikesByPostId(long postId) {
        return likeRepository.findAllLikesByPostId(postId).size();
    }

    @Override
    public void likePost(Long postId) {
        Assert.notNull(postId, "postId must not be null");
        postService.makeSurePostExist(postId);

        Long authorizedUserId = authorizedUserService.getAuthorizedUserId();

        likeRepository.findLikeByPostIdAndUserId(postId, authorizedUserId)
                        .ifPresent(like -> {throw new LikeAlreadyExistException("Like is already exists");});

        likeRepository.save(likeMapper.mapToEntity(LikeDto.builder()
                .postId(postId)
                .userId(authorizedUserId)
                .build()));
    }

    @Override
    public void dislikePost(Long postId) {
        Assert.notNull(postId, "postId must not be null");
        postService.makeSurePostExist(postId);

        Long authorizedUserId = authorizedUserService.getAuthorizedUserId();

        Like like = likeRepository.findLikeByPostIdAndUserId(postId, authorizedUserId)
                .orElseThrow(() -> new NoSuchElementException("like is not exist"));

        likeRepository.delete(like);
    }

    @Override
    public boolean isLikeExist(long postId, long userId) {
        return likeRepository.findLikeByPostIdAndUserId(postId, userId)
                .isPresent();
    }
}
