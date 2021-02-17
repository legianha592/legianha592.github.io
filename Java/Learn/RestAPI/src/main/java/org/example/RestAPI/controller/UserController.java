package org.example.RestAPI.controller;


import org.example.RestAPI.model.User;
import org.example.RestAPI.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity addUser(@RequestBody User user){
//        System.out.println("id = " + user.getId());
//        System.out.println(user.getUser_name());
//        System.out.println(user.getPassword());
//        System.out.println(user.getCreated_date());
//        System.out.println(user.getModified_date());

        userService.addUser(user);

        return ResponseEntity.ok().body(user);
    }


}
