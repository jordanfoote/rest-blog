package com.example.restblog.web;

import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping(value = "api/users", headers = "Accept=application/json")
public class UserController {

    private List<User> users = setUserList();

    @GetMapping
    public List<User> getAll() {
        return users;
    }

    @GetMapping("{id}")
    public User getById(@PathVariable Long id) {
        for (User user : getAll()) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    @GetMapping("username")
    public User getByUsername(@RequestParam String username) {
        for (User user : getAll()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @GetMapping("email")
    public User getByEmail(@RequestParam String email) {
        for (User user : getAll()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    @PostMapping
    public void createUser(@RequestBody User newUser) {
        System.out.println(newUser);
        users.add(newUser);
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

    private List<User> setUserList(){
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "Jordan Foote", "jordan.foote@outlook.com", "12345"));
        users.add(new User(2L, "Mr. Sirguy", "dudebro@gmail.com", "54321"));
        return users;
    }
}
