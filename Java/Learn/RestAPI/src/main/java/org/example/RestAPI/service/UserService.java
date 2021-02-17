package org.example.RestAPI.service;

import org.example.RestAPI.model.User;
import org.example.RestAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{
    @Autowired
    UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.saveAndFlush(user);
    }
}
