package com.infosys.demo.DemoApp.response;

import java.util.List;

import com.infosys.demo.DemoApp.entity.Book;
import com.infosys.demo.DemoApp.entity.StatusBook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse  {

    private Long id;

    private String author;
    
    private String title;

    private StatusBook status;

}
