package com.infosys.demo.DemoApp.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRequest {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;
}
