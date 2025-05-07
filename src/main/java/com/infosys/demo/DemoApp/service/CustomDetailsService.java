package com.infosys.demo.DemoApp.service;

import java.util.Optional;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.infosys.demo.DemoApp.entity.Users;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomDetailsService implements UserDetailsService{



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> optionalUsers =  this.userService.findByUsername(username);
        UserDetails userDetails = null;
        return optionalUsers
                .orElseThrow(() -> new UsernameNotFoundException("User not found")) ;

    }

    public CustomDetailsService(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;

}
