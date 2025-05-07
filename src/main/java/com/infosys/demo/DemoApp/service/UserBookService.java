package com.infosys.demo.DemoApp.service;

import java.util.List;
import java.util.Optional;

import com.infosys.demo.DemoApp.entity.UserBook;
import com.infosys.demo.DemoApp.request.UserBookRequest;

public interface UserBookService {
    Optional<UserBook> findBorrowedBooksByUser(UserBookRequest userBookReques);
    List<UserBook> borrowBook(UserBookRequest userBookRequest);
    void returnBook(UserBookRequest userBookRequest);
}
