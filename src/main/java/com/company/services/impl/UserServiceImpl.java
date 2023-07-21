package com.company.services.impl;

import com.company.dao.UserDAO;
import com.company.models.User;
import com.company.services.UserService;

import java.util.Base64;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiceImpl() {
        userDAO = new UserDAO();
    }

    public boolean registerUser(String username, String password) {
        // Check if the username already exists in the database
        User existingUser = userDAO.getUserWithUsername(username);
        if (existingUser != null) {
            return false;
        }
        User newUser = new User();
        newUser.setUsername(username);

        // Encrypt the password using BCrypt before saving it
        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        newUser.setPassword(encodedPassword);

        userDAO.registerUser(newUser);
        System.out.println("User registered successfully.");
        return true; // Registration successful.
    }

    public User loginUser(String username, String password) {
        User userWithUsername =  userDAO.getUserWithUsername(username);
        if (userWithUsername != null) {
            String encodedPasswordFromDatabase = userWithUsername.getPassword();
            // Decode the stored password using Base64
            String storedPassword = new String(Base64.getDecoder().decode(encodedPasswordFromDatabase));
            // Compare the entered password with the decoded stored password
            if (password.equals(storedPassword)) {
                return userWithUsername;
            }
        }
        return null;
    }

}
