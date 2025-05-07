package com.infosys.demo.DemoApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.demo.DemoApp.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitle(String title);


}
