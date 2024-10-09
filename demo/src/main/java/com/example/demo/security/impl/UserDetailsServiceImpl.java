package com.example.demo.security.impl;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Start find user with user name->" + username);
        try {
            Optional<User> useropt = userRepository.findByUsername(username);
            if(useropt.isPresent()){
                User user=useropt.get();
                System.out.println("Start find user with user name->" + user.getUsername() + "," + user.getPassword());
                return UserDetailsImpl.build(user);
            }else{
                List<User> userList=userRepository.findAll();
                userList.stream().forEach(u->System.out.println("User ID->"+u.getUserId()+",Name->"+u.getUsername()+",email"+u.getEmail()));
            }
                 //   .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        } catch (Exception e) {
          System.out.println("Error"+e.getMessage());
        }
       return null;
    }
}
