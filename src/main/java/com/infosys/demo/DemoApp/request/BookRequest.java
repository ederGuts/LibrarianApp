package com.infosys.demo.DemoApp.request;

import com.infosys.demo.DemoApp.entity.StatusBook;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import com.infosys.demo.DemoApp.entity.Book;
import com.infosys.demo.DemoApp.entity.Genre;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class BookRequest {

    private String title;
    private String author;
    private Genre genre;
    private StatusBook status;
    

}
