package com.infosys.demo.DemoApp.response;

import java.util.List;

import com.infosys.demo.DemoApp.entity.Users;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private String role;

    
}
