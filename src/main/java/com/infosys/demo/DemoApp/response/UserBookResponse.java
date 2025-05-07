package com.infosys.demo.DemoApp.response;

import java.util.List;

import lombok.*;
import org.springframework.security.core.userdetails.User;

import com.infosys.demo.DemoApp.entity.*;
import com.infosys.demo.DemoApp.util.GenericConverter;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserBookResponse {
    private Long id;
    private UserResponse user;
    private BookResponse book;
        
}
