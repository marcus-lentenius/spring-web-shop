package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    void save(User user);
    String encryptedPassword(User user);
    User findByUserName(String userName);
}
