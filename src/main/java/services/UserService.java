package services;

import models.User;
import repositories.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User user) {
        userRepository.addUser(user);
    }

    public boolean authenticateUser(String username, String password) {
        Optional<User> userOpt = userRepository.getUserByUsername(username);
        return userOpt.isPresent() && userOpt.get().getPassword().equals(password);
    }

    public boolean updateUser(User updatedUser) {
        return userRepository.updateUser(updatedUser);
    }

    public boolean deleteUser(String username) {
        return userRepository.deleteUser(username);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
