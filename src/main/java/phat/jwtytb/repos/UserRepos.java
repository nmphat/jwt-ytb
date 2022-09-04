package phat.jwtytb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import phat.jwtytb.model.User;

import java.util.Optional;

public interface UserRepos extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
