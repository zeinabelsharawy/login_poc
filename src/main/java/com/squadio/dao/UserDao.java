package com.squadio.dao;

import com.squadio.entity.User;

public interface UserDao {
    User save(User user);

    User findById(Long id);

    User findByUsername(String username);
}
