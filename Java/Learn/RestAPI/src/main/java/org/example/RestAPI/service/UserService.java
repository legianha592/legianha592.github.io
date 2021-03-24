package org.example.RestAPI.service;

import org.example.RestAPI.exporter.UserExcelExporter;
import org.example.RestAPI.model.User;
import org.example.RestAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

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

    public Optional<User> findByUser_name(String name){
        return userRepository.findByUser_name(name);
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public ByteArrayInputStream load() {
        List<User> listUser = userRepository.findAll();

        ByteArrayInputStream in = UserExcelExporter.UserEntityToExcel(listUser);
        return in;
    }
}
