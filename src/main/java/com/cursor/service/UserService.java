package com.cursor.service;

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

    public void createUser(User user) {
        userRepository.save(new User(user.getId(), user.getUsername(), user.getPassword(), user.isActive(), user.getRole()));
    }

    public void updateUser(User user) {
        userRepository.updateUserById(user.getId(), user.getUsername(), user.getPassword(), user.getRole(), user.isActive());
    }

    public void deleteUser(User user) {
        userRepository.deleteById(user.getId());
    }

    public User findById(Integer id) {
        return userRepository.findUserById(id);
    }
}
