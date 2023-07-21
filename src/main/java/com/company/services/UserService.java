package com.company.services;

import com.company.models.User;

public interface UserService {
    boolean registerUser(String username, String password);

    User loginUser(String username, String password);
}
