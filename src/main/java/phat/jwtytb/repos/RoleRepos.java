package phat.jwtytb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import phat.jwtytb.model.Role;

import java.util.Optional;

public interface RoleRepos extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
