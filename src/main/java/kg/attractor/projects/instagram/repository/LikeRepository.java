package kg.attractor.projects.instagram.repository;

import kg.attractor.projects.instagram.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    List<Like> findAllLikesByPostId(long postId);

    Optional<Like> findLikeByPostIdAndUserId(long postId, long userId);
}
