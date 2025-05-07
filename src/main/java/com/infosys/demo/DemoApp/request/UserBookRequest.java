package com.infosys.demo.DemoApp.request;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserBookRequest {
    
    private Long userId;

    private Long bookId;
    
    private List<Long> listBook;
    
}
