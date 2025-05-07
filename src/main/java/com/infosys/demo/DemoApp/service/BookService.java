package com.infosys.demo.DemoApp.service;

import java.util.List;

import com.infosys.demo.DemoApp.entity.Book;
import com.infosys.demo.DemoApp.request.BookRequest;

public interface BookService {

    public List<Book> findAll();

    public Book addBook(Book book);

}
