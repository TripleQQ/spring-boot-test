package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {

   List<User> getUserByRoleName(String roleName);

    User getUserByName(String userName);

    User getUserByID(long userId);
}
