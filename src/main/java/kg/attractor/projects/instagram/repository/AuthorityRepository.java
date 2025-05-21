package kg.attractor.projects.instagram.repository;

import kg.attractor.projects.instagram.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findAuthorityByName(String authorityName);
}
