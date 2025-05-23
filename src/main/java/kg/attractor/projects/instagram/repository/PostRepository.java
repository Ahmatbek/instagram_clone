package kg.attractor.projects.instagram.repository;

import kg.attractor.projects.instagram.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.user.id = :userId")
    List<Post> findUsersPosts(Long userId);

    @Query("select p from Post p where p.user.id = :userId")
    Page<Post> findUserPosts(Long userId, Pageable pageable);

    @Query("select p  from Post p where p.user.id=:userId and p.id=:postId")
    Optional<Post> findByPostIdAndUserId(Long userId, Long postId);

    @Query("select p from Post p " +
            "left join Like l on l.post.id = p.id " +
            "group by p " +
            "order by count(l) desc")
    Page<Post> findAllPostsOrderedByIdLike(Pageable pageable);

    @Query("select p from Post p where p.user.id in :ids")
    Page<Post> findUsersPostsByUsersIds(List<Long> ids, Pageable pageable);
}
