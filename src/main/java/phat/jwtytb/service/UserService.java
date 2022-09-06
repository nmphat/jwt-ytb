package phat.jwtytb.service;

import phat.jwtytb.model.Role;
import phat.jwtytb.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    Role saveRole(Role role);

    boolean allRoleToUser(String username, String roleName);

    User getUserById(Long id);

    // User getUserByName(String name);
    User getUserByUsername(String username);

    Role getRoleById(Long id);

    Role getRoleByName(String name);

    List<User> getUsers();


}
