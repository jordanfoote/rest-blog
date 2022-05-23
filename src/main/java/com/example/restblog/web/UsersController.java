package com.example.restblog.web;

import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import com.example.restblog.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping(value = "api/users", headers = "Accept=application/json")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getUsersList();
    }

    @GetMapping("{id}")
    public User getById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("username")
    public User getByUsername(@RequestParam String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("email")
    public User getByEmail(@RequestParam String email) {
        System.out.println(email);
        return null;
    }

    @PostMapping
    public void createUser(@RequestBody User newUser) {
        userService.getUsersList().add(newUser);
    }

    @PostMapping("{username}")
    public void addUserPost(@PathVariable String username, @RequestBody Post newPost){
        User user = userService.getUserByUsername(username);
        user.getPosts().add(newPost);
    }

    @PutMapping("{id}/updatePassword")
    private void updatePassword(@PathVariable Long id, @RequestParam(required = false) String oldPassword, @Valid @Size(min= 3) @RequestParam String newPassword) {
        User userToUpdate = getById(id);
        userToUpdate.setPassword(newPassword);
        System.out.println(userToUpdate.getPassword());
    }

    // @PutMapping("{id}")
    // private void updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
    //     for (User user : users) {
    //         if (user.getId().equals(id)) {
    //             user.setUsername(updatedUser.getUsername());
    //             user.setEmail(updatedUser.getEmail());
    //             user.setPassword(updatedUser.getPassword());
    //         }
    //     }
    // }

    @DeleteMapping("{id}")
    private void deleteUser(@PathVariable Long id) {
        System.out.println("Deleting user with the id: " + id);
    }
}
