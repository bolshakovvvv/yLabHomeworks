package org.example.ylabhomework;

import static org.junit.jupiter.api.Assertions.*;

import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.UserRepository;
import services.UserService;

import java.util.Optional;

public class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = new UserRepository();
        userService = new UserService(userRepository);
    }

    @Test
    public void testRegisterUser() {
        User user = new User("testUser", "password", "client");
        userService.registerUser(user);

        Optional<User> registeredUser = userRepository.getUserByUsername("testUser");
        assertTrue(registeredUser.isPresent());
        assertEquals("testUser", registeredUser.get().getUsername());
    }

    @Test
    public void testAuthenticateUser() {
        User user = new User("testUser", "password", "client");
        userService.registerUser(user);

        assertTrue(userService.authenticateUser("testUser", "password"));
        assertFalse(userService.authenticateUser("testUser", "wrongPassword"));
    }
}
