package com.infosys.demo.DemoApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.demo.DemoApp.entity.UserBook;
import com.infosys.demo.DemoApp.entity.Users;

public interface UserBookRepository extends JpaRepository<UserBook, Long>{
    
    Optional<UserBook> findByUsers(Users users);
    Optional<UserBook> findByUsersIdAndBooksId(Long userId, Long bookId);

}
