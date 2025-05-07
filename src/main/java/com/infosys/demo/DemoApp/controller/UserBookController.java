package com.infosys.demo.DemoApp.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.infosys.demo.DemoApp.entity.Book;
import com.infosys.demo.DemoApp.response.BookResponse;
import com.infosys.demo.DemoApp.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.infosys.demo.DemoApp.entity.UserBook;
import com.infosys.demo.DemoApp.request.UserBookRequest;
import com.infosys.demo.DemoApp.response.UserBookResponse;
import com.infosys.demo.DemoApp.service.UserBookService;


@RestController
@RequestMapping("/borrow")
@Slf4j
public class UserBookController extends BaseController<UserBook,UserBookResponse> {

    @PostMapping("/{bookId}")
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public ResponseEntity<List<UserBookResponse>> borrowBook(@PathVariable(name="bookId") Long bookId
                                        ,@RequestParam Long userId) {
        UserBookRequest userBookRequest = UserBookRequest.builder()
            .userId(userId)
            .listBook(List.of(bookId))
            .build();
        log.info("borrowBook {}",userId);
        
        List<UserBookResponse> list = userBookService.borrowBook(userBookRequest).stream().map(userBook->{
            return toDto(userBook, UserBookResponse.class);
        }).collect(Collectors.toList()) ;

        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/return/{bookId}")
    public ResponseEntity<Void> returnBook(@PathVariable(name="bookId") long bookId,@RequestParam Long userId) {
        this.userBookService.returnBook(UserBookRequest.builder()
                .bookId(bookId)
                .userId(userId)
                .build());
        return ResponseEntity.ok().build();
    }

    public UserBookController(UserBookService userBookService) {
        this.userBookService = userBookService;
    }

    private final UserBookService userBookService;

    @Override
    public UserBookResponse toDto(UserBook entity, Class<UserBookResponse> dto) {
//        ModelMapper modelMapper = new ModelMapper();
//        TypeMap<UserBook,UserBookResponse> propertyMapper = modelMapper.getTypeMap(UserBook.class,dto);
//        if(propertyMapper == null) {
//            propertyMapper = modelMapper.createTypeMap(entity, dto);
//        }
//
//        propertyMapper.addMappings(
//                mapping -> {
//                    mapping.map(source ->
//                            source.getBooks(), UserBookResponse::setBook);
//                    mapping.map(source ->
//                            source.getUsers(), UserBookResponse::setUser);
//                });
//
//        return modelMapper.map(entity, UserBookResponse.class);

        /*
       TypeMap<UserBook,UserBookResponse> typeMap = this.getPropertyMapper(UserBook.class,UserBookResponse.class);

           typeMap.addMappings(
                   mapping -> {
                       mapping.map(source ->
                               source.getBooks(), UserBookResponse::setBook);
                       mapping.map(source ->
                               source.getUsers(), UserBookResponse::setUser);
                   });

        return super.toDto(entity,dto) ;*/

        return UserBookResponse.builder()
                .id(entity.getId())
                .user(UserResponse.builder()
                        .id(entity.getUsers().getId())
                        .email(entity.getUsers().getEmail())
                        .role(entity.getUsers().getRole())
                        .build())
                .book(BookResponse.builder()
                        .id(entity.getBooks().getId())
                        .title(entity.getBooks().getTitle())
                        .author(entity.getBooks().getAuthor())
                        .status(entity.getBooks().getStatus())
                        .build())
                .build();

    }
}
