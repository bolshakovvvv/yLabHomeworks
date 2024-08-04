package repositories;

import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public Optional<User> getUserByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }

    public boolean updateUser(User updatedUser) {
        Optional<User> userOpt = getUserByUsername(updatedUser.getUsername());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setPassword(updatedUser.getPassword());
            user.setRole(updatedUser.getRole());
            return true;
        }
        return false;
    }

    public boolean deleteUser(String username) {
        return users.removeIf(user -> user.getUsername().equals(username));
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}
