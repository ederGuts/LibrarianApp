package com.infosys.demo.DemoApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.infosys.demo.DemoApp.entity.Book;
import com.infosys.demo.DemoApp.entity.StatusBook;
import com.infosys.demo.DemoApp.exception.ResourceFoundException;
import com.infosys.demo.DemoApp.repository.BookRepository;
import com.infosys.demo.DemoApp.request.BookRequest;

@Service
public class BookServiceImp implements BookService {    
    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book addBook(Book book) {

        Optional<Book> bookRegistered = this.bookRepository.findByTitle(book.getTitle());

        if(bookRegistered.isPresent()) {
            throw new ResourceFoundException(String.format("Book title %s already exist", book.getId()));
        }

        book.setStatus(StatusBook.AVAILABLE);
        return this.bookRepository.save(book);
    }


    public BookServiceImp(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    private final BookRepository bookRepository;

}
