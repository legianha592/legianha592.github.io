package org.example.RestAPI.service;

import org.example.RestAPI.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> findAll();

    void addUser(User user);

    Optional<User> findByUser_name(String name);

    Optional<User> findById(Long id);
}
