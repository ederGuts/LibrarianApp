package com.infosys.demo.DemoApp.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.infosys.demo.DemoApp.request.UserRequest;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.demo.DemoApp.entity.Users;
import com.infosys.demo.DemoApp.response.BaseResponse;
import com.infosys.demo.DemoApp.response.UserResponse;
import com.infosys.demo.DemoApp.service.UserService;


@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController<Users, UserResponse>{

    public UserController(UserService employeeService) {
        this.userService = employeeService;
    }

    @GetMapping("/id")
    public Map<String, Object> getUserId(@AuthenticationPrincipal Jwt jwt) {
        // Extrae el user_id del token JWT
        String userId = jwt.getClaimAsString("id");
        Map claims = Map.of ("id", userId
                ,"email",jwt.getClaimAsString("email"),
                "authorities",jwt.getClaimAsStringList ("authorities") );
        return claims;
    }

    @GetMapping("/fetch-all")
    @PreAuthorize("hasAnyAuthority('LIBRARIAN','USER')")
    public ResponseEntity<List<UserResponse>> getAll() {
        List<Users> list = this.userService.findAll();
        
        List<UserResponse> listUserResponse = list.stream().map(users->{
            return this.toDto(users, UserResponse.class);
        }).collect(Collectors.toList()) ;

        return ResponseEntity.ok(listUserResponse);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public ResponseEntity<UserResponse> addUser(UserRequest userRequest) {
         UserResponse userResponse = toDto(userService.saveUser(userRequest),UserResponse.class);
        return ResponseEntity.ok(userResponse);
    }



    private final UserService userService;
    

}
