package com.infosys.demo.DemoApp.service;

import java.util.List;
import java.util.Optional;

import com.infosys.demo.DemoApp.entity.Users;
import com.infosys.demo.DemoApp.request.UserRequest;

public interface UserService {
    public List<Users> findAll();
    public Optional<Users> findByUsername(String username);
    public Users saveUser(UserRequest user);
}
