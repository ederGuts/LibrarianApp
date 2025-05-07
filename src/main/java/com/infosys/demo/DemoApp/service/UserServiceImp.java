package com.infosys.demo.DemoApp.service;

import java.util.List;
import java.util.Optional;

import com.infosys.demo.DemoApp.request.UserRequest;
import org.springframework.stereotype.Service;

import com.infosys.demo.DemoApp.entity.Users;
import com.infosys.demo.DemoApp.exception.ResourceFoundException;
import com.infosys.demo.DemoApp.repository.UserRepository;

@Service
public class UserServiceImp implements UserService{

    public UserServiceImp(UserRepository employeeRepository) {
        this.usersRepository = employeeRepository;
    }

    @Override
    public List<Users> findAll() {
        return this.usersRepository.findAll();
    }


    @Override
    public Optional<Users> findByUsername(String username) {
        return this.usersRepository.findByUsername(username);        
    }

    public Users saveUser(UserRequest userRequest ) {
        Users users = Users.builder()
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .email(userRequest.getEmail())
                .role(userRequest.getRole())
                .build();
         this.usersRepository.findByUsername(users.getUsername())
         .ifPresent(userFound-> {
            throw new ResourceFoundException(String.format("User found %s ",userFound.getId()));
         });

         return this.usersRepository.save(users);
    }

    private final UserRepository usersRepository;

}
