package com.infosys.demo.DemoApp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.infosys.demo.DemoApp.entity.StatusBook;
import org.springframework.stereotype.Service;

import com.infosys.demo.DemoApp.entity.Book;
import com.infosys.demo.DemoApp.entity.UserBook;
import com.infosys.demo.DemoApp.entity.Users;
import com.infosys.demo.DemoApp.exception.NotFoundException;
import com.infosys.demo.DemoApp.repository.BookRepository;
import com.infosys.demo.DemoApp.repository.UserBookRepository;
import com.infosys.demo.DemoApp.repository.UserRepository;
import com.infosys.demo.DemoApp.request.UserBookRequest;

@Service
public class UserBookServiceImp implements UserBookService {

    @Override
    public Optional<UserBook> findBorrowedBooksByUser(UserBookRequest userBookRequest) {
        Optional<Users> optionalUsers = this.userRepository.findById(userBookRequest.getUserId() );
        if(!optionalUsers.isPresent()) {
            throw new NotFoundException("User id %d not found");
        }

        Optional<UserBook> optionalUserBook = this.userBookRepository.findByUsers(optionalUsers.get());
        return optionalUserBook;
    }

    public List<UserBook> borrowBook(UserBookRequest userBookRequest) {
        
        Optional<Users> optionalUsers = this.userRepository.findById(userBookRequest.getUserId());
        if(!optionalUsers.isPresent()) {
            throw new NotFoundException(String.format("User id %d not found.",userBookRequest.getUserId() ));
        }

        List<UserBook> listUserBook = userBookRequest.getListBook()
            .stream()
            .map(bookId-> { 
                Book book = this.bookRepository.findById(bookId).orElseThrow(
                    ()->new NotFoundException(String.format("Book id %d not found",bookId))
                );
                book.setStatus(StatusBook.BORROWED);
                return UserBook.builder()
                    .users(optionalUsers.get())
                    .books(book).build();
             })
             .collect(Collectors.toList());

        return this.userBookRepository.saveAll(listUserBook);
    }

    @Override
    public void returnBook(UserBookRequest userBookRequest) {
        UserBook userBook =
                this.userBookRepository.findByUsersIdAndBooksId(userBookRequest.getUserId(),userBookRequest.getBookId())
                        .orElseThrow(()-> new NotFoundException(String.format("There is no book id %d related to user %d",userBookRequest.getBookId(),userBookRequest.getUserId())));
        userBook.getBooks().setStatus(StatusBook.AVAILABLE);

        this.userBookRepository.delete(userBook);
    }

    public UserBookServiceImp(UserBookRepository userBookRepository
            ,UserRepository userRepository
            ,BookRepository bookRepository) {
        this.userBookRepository = userBookRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    private final UserBookRepository userBookRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
}
