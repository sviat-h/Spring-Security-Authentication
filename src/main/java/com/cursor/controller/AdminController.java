package com.cursor.controller;

import com.cursor.models.User;
import com.cursor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/byUsername")
    public User findByUsername(@RequestParam String username) {
        return userService.findUserByUsername(username);
    }

    @POST
    @Path("/update/{id}")
    public Response updateUSer(@RequestParam(name = "id") Integer id, User user) {
        User updatedUser = userService.findById(id);

        updatedUser.setUsername(user.getUsername());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setActive(user.isActive());
        updatedUser.setRole(user.getRole());

        userService.updateUser(updatedUser);

        return Response.ok().build();
    }

    @POST
    @Path("/create")
    public Response createUser(User user) {
        userService.createUser(user);

        return Response.ok().build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteUser(@PathParam("id") Integer id) {
        User getUser = userService.findById(id);

        userService.deleteUser(getUser);

        return Response.ok().build();
    }
}
