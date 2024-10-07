package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> getUserByRoleName(String roleName) {
        return userRepository.findByRoleList_RoleName(roleName);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByName(String userName) {
        Optional<User> userOption= userRepository.findByUserName(userName);
        if(userOption.isPresent()) return userOption.get();
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByID(long userId) {
        Optional<User> userOption= userRepository.findById(userId);
        if(userOption.isPresent()) return userOption.get();
        return null;
    }
}
