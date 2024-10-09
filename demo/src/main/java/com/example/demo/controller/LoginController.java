package com.example.demo.controller;

import com.example.demo.dto.LoginDetailDto;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import com.example.demo.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDetailDto authRequest) {

        System.out.println("************"+authRequest.getName()+",->"+authRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok().body(jwtUtil.generateToken(userDetails));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try{
            System.out.println("name:"+user.getUsername()+", Email"+user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
            return  ResponseEntity.ok().body("User registered successfully!");
        }catch (Exception e){
            System.out.println("Error happen"+e.getMessage());
            return  ResponseEntity.status(400).body("This is error due to "+e.getMessage());

        }

    }
}
