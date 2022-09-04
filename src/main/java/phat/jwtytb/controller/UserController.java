package phat.jwtytb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import phat.jwtytb.model.ResponseObject;
import phat.jwtytb.model.User;
import phat.jwtytb.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    ResponseEntity<ResponseObject> getUsers() {
        List<User> users = userService.getUsers();

        return ResponseEntity.ok().body(
                new ResponseObject(
                        "ok",
                        String.format("Retrieve %d users in db", users.size()),
                        users
                )
        );
    }

}
