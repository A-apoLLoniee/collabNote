package ma.collabnote.collabnote.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import ma.collabnote.collabnote.entity.User;
import ma.collabnote.collabnote.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public List<User> getAllUsers () {
        return userService.getAllUsers();
    }

    @GetMapping("/api/users/{userId}")
    public User getUserById(@PathVariable UUID userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/api/users")
    public User addUser(@Valid @RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/api/users/{userId}")
    public User changeName(@PathVariable UUID userId, @NotBlank @RequestBody String name) {
        return userService.changeName(userId, name);
    }

    @PutMapping("/api/users/{userId}/password")
    public User changePassword(@PathVariable UUID userId, @NotBlank @RequestBody String password) {
        return userService.changePassword(userId, password);
    }

    @DeleteMapping("/api/users/{userId}")
    public void removeUser(@PathVariable UUID userId) {
        userService.removeUser(userId);
    }
}
