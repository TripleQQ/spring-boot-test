package com.example.demo.controller;

import com.example.demo.dto.LoginDetailDto;
import com.example.demo.model.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnCheckpointRestore;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login/")
public class LoginController {


    @PostMapping("user")
    public ResponseEntity<User> getUserInfo(@RequestBody LoginDetailDto loginDetail){
         System.out.println("111");
         return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}
