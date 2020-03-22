package com.cursor.controller;

import com.cursor.models.Role;
import com.cursor.models.User;
import com.cursor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/byUsername")
    public User findByUsername(String username) {
        return userService.findUserByUsername(username);
    }

    @PostMapping(path = "/createUser")
    public void createUser(Integer id, String username, String password, boolean active, Role role) {
        userService.createUser(id, username, password, active, role);
    }

    @PostMapping(path = "/updateUser")
    public void updateUser(Integer id, String username, String password, Role role, boolean active) {
        userService.updateUser(id, username, password, role, active);
    }

    @PostMapping(path = "/updateActive")
    public void updateActive(Integer id, boolean active) {
        userService.updateActive(id, active);
    }

    @PostMapping(path = "/updateRole")
    public void updateRole(Integer id, Role role) {
        userService.updateRole(id, role);
    }

    @DeleteMapping(path = "/deleteUser")
    public void deleteUserById(Integer id) {
        userService.deleteUserById(id);
    }
}
