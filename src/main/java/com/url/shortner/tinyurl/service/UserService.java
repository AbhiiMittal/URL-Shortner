package com.url.shortner.tinyurl.service;

import com.url.shortner.tinyurl.model.User;

import java.util.List;

public interface UserService {
    List<User> getUserList();
    User getUserDetails(Long id);
    String setUserDetails(User user);
    String createNewUser(User user);
    String deleteUser(Long id);
}
