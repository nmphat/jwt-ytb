package phat.jwtytb.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import phat.jwtytb.model.Role;
import phat.jwtytb.model.User;
import phat.jwtytb.repos.RoleRepos;
import phat.jwtytb.repos.UserRepos;
import phat.jwtytb.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
public class DummyDb {
    @Autowired
    private UserService userService;

    @Bean
    CommandLineRunner initDummyDb() {
        return args -> {

            // create dummy roles
            List<Role> roles = new ArrayList<>() {{
                add(new Role(1L, "admin"));
                add(new Role(2L, "manager"));
                add(new Role(3L, "user"));
            }};

            // save dummy roles
            for (Role role : roles) {
                userService.saveRole(role);
            }

            // create dummy users
            List<User> users = new ArrayList<>() {{
                for (long i = 1; i <= 100; i++) {
                    add(new User(i, "name" + i, "username" + i, "12345"));
                }
            }};

            // save dummy users
            for (User user : users) {
                userService.saveUser(user);
            }

            // 5 admins
            for (int i = 0; i < 5; i++){
                userService.allRoleToUser(
                        users.get(i).getUsername(),
                        "admin");
            }

            // 5 managers
            for (int i = 5; i < 10; i++){
                userService.allRoleToUser(
                        users.get(i).getUsername(),
                        "manager");
            }

            // 100 users
            for (User user : users){
                userService.allRoleToUser(
                        user.getUsername(),
                        "user");
            }
        };
    }
}
