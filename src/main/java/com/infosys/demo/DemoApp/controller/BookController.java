package com.infosys.demo.DemoApp.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.infosys.demo.DemoApp.entity.StatusBook;
import com.infosys.demo.DemoApp.request.BookRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import com.infosys.demo.DemoApp.entity.Book;

import com.infosys.demo.DemoApp.response.BookResponse;
import com.infosys.demo.DemoApp.service.BookService;


@RestController
@RequestMapping("/book")
@Slf4j
public class BookController extends BaseController<Book,BookResponse> {
    
    @GetMapping("/fetch-all")
    @PreAuthorize("hasAnyAuthority('LIBRARIAN','USER')")
    public ResponseEntity<List<BookResponse>> getAll() {
//        var claim =  jwt.getClaim("authorities");
//        log.info("Claim " +claim.toString());
        List<BookResponse> listBookResponse = this.bookService.findAll().stream()
        .map(book->{
            return toDto(book, BookResponse.class);
        })
        .collect(Collectors.toList());

        return ResponseEntity.ok(listBookResponse); 
    }

    @PostMapping("/add")
    public ResponseEntity<BookResponse> addBook(@RequestBody BookRequest bookRequest) {
        log.info(" bookRequest {}",bookRequest);
        Book book = Book.builder()
                .author(bookRequest.getAuthor())
                .title(bookRequest.getTitle())
                .genre(bookRequest.getGenre())
                .status(bookRequest.getStatus())
                .build();
        BookResponse bookResponse = toDto(this.bookService.addBook(book),BookResponse.class);
        return ResponseEntity.ok(bookResponse);
    }

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    private final BookService bookService;
}
