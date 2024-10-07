package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private static final Logger log= LoggerFactory.getLogger(UserController.class);
    private UserService userServiceImpl;

    public UserController(UserService userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/dh/{userName}")
    public ResponseEntity<?> getUserByName(@PathVariable("userName") String userName){
        System.out.println("11144");

        User user=userServiceImpl.getUserByName(userName);
     if(user==null) return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No able to find such user");
     return ResponseEntity.ok(user);

    }

    @GetMapping("/hello")
    public ResponseEntity<?> getTesting(){
        log.debug("kkk");
        System.out.println("211");

        return ResponseEntity.ok("Hello World");

    }
}
