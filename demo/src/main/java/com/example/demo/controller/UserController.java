package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private static final Logger log= LoggerFactory.getLogger(UserController.class);
    private final UserService userServiceImpl;



    @GetMapping("/userDetail/{userName}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getUserByName(@PathVariable("userName") String userName){
        System.out.println("11144");

        User user=userServiceImpl.getUserByName(userName);
     if(user==null) return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No able to find such user");
     return ResponseEntity.ok(user);

    }


    @GetMapping("/my-profile")
    public ResponseEntity<?> getUserDetail(){
        System.out.println("getUserDetail");
        try{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Extract username from the authentication
        String username = authentication.getName();
        User user=userServiceImpl.getUserByName(username);
        if(user==null) return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No able to find such user");
        return ResponseEntity.ok(user);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("/hello")
    public ResponseEntity<?> getTesting(){
        log.debug("kkk");
        System.out.println("211");

        return new ResponseEntity<>("thi sis body message", HttpStatusCode.valueOf(200));

    }
}
