package org.example.RestAPI.service;

import org.example.RestAPI.model.User;

import java.util.List;

public interface IUserService {
    public List<User> findAll();

    public void addUser(User user);
}
