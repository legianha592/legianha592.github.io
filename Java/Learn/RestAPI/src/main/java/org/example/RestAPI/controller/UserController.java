package org.example.RestAPI.controller;


import org.example.RestAPI.model.Message;
import org.example.RestAPI.model.User;
import org.example.RestAPI.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping("/all")
    public List<User> findAll(){
        return userService.findAll();
    }

    @PostMapping("/signup")
    public ResponseEntity<Message<User>> addUser(@RequestBody User user){
        userService.addUser(user);

        Message<User> message = new Message<>(
                new String("Đăng kí thành công"),
                user
        );
        return new ResponseEntity<Message<User>>(message, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user){

        return new ResponseEntity(user, HttpStatus.OK);
    }
}
