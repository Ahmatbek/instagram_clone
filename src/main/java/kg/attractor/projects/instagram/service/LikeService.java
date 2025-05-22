package kg.attractor.projects.instagram.service;

public interface LikeService {
    long findAllLikesByPostId(long postId);

    void likePost(Long postId);

    void dislikePost(Long postId);

    boolean isLikeExist(long postId, long userId);
}
