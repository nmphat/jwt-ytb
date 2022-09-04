package phat.jwtytb.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import phat.jwtytb.model.Role;
import phat.jwtytb.model.User;
import phat.jwtytb.repos.RoleRepos;
import phat.jwtytb.repos.UserRepos;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepos userRepos;
    private final RoleRepos roleRepos;

    @Override
    public User saveUser(User user) {
        return userRepos.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepos.save(role);
    }

    @Override
    public boolean allRoleToUser(String username, String roleName) {
        try {
            User foundUser = userRepos.findByUsername(username).orElseThrow();
            Role foundRole = roleRepos.findByName(roleName).orElseThrow();

            foundUser.getRoles().add(foundRole);

            log.info("Added role {} to user {}", roleName, username);
            return true;
        } catch (Exception e) {
            log.error(e);

            return false;
        }
    }

    @Override
    public User getUserById(Long id) {
        try {

            User user = userRepos.findById(id).orElseThrow();

            log.info("Retrieved user with id {} successfully", id);

            return user;
        } catch (Exception e) {
            log.error(e);

            return new User();
        }
    }

    @Override
    public User getUserByUsername(String username) {
        try {

            User user = userRepos.findByUsername(username).orElseThrow();

            log.info("Retrieved user with username {} successfully", username);

            return user;
        } catch (Exception e) {
            log.error(e);

            return new User();
        }
    }

    @Override
    public List<User> getUsers() {
        return userRepos.findAll();
    }
}
