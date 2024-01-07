package com.squadio.service;

import com.squadio.entity.User;

public interface UserService {
    User login(String username, String password);
}
