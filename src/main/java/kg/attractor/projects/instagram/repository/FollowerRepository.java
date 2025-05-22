package kg.attractor.projects.instagram.repository;

import kg.attractor.projects.instagram.model.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FollowerRepository extends JpaRepository<Follower, Long> {
    @Query("select f from Follower f where f.userFollower.id=:id")
    List<Follower> getFollowers(long id);
    @Query("select f from Follower f where f.userReceiver.id=:id")
    List<Follower> getReceivers(long id);
    @Query("select f from Follower f" +
            " where f.userFollower.id=:followerId and f.userReceiver.id=:receiverId")
    Optional<Follower> findByUserFollowerIdAndUserReceiver(long followerId, long receiverId);
}
