package com.infosys.demo.DemoApp.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="USERS_BOOK")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserBook {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name="book_id")
    private Book books;

    private LocalDateTime borrowDate = LocalDateTime.now();

    private LocalDateTime returnDate;
}
