package com.cursor.service;

import com.cursor.models.Role;
import com.cursor.models.User;
import com.cursor.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void createUser(Integer id, String username, String password, boolean active, Role role) {
        userRepository.save(new User(id, username, password, active, role));
    }

    public void updateUser(Integer id, String username, String password, Role role, boolean active) {
        userRepository.updateUserById(id, username, password, role, active);
    }

    public void updateActive(Integer id, boolean active) {
        userRepository.updateActiveById(id, active);
    }

    public void updateRole(Integer id, Role role) {
        userRepository.updateRoleById(id, role);
    }

    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }
}
