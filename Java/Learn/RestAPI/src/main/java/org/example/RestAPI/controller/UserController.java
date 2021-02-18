package org.example.RestAPI.controller;

import org.example.RestAPI.finalstring.FinalMessage;
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
import java.util.Optional;

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
        Optional<User> findUser = userService.findByUser_name(user.getUser_name());
        Message<User> message;
        if (findUser.isEmpty()){
            userService.addUser(user);
            message = new Message<>(FinalMessage.SIGNUP_SUCCESS, user);
        }
        else{
            message = new Message<>(FinalMessage.USERNAME_EXISTED, null);
        }
        return new ResponseEntity<Message<User>>(message, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user){
//        System.out.println(user.getUser_name());
        Optional<User> findUser = userService.findByUser_name(user.getUser_name());
        Message<User> message;
        if (findUser.isEmpty()){
            message = new Message<>(FinalMessage.NO_USER, null);
        }
        else{
            if (!user.getPassword().equals(findUser.get().getPassword())){
                message = new Message<>(FinalMessage.WRONG_PASSWORD, null);
            }
            else{
                message = new Message<>(FinalMessage.LOGIN_SUCCESS, findUser.get());
            }
        }
        return new ResponseEntity(message, HttpStatus.OK);
    }
}
