package com.example.ls4_1_author.Model.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "borrow_tickets")
@Data
public class BorrowTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;
    private LocalDate borrowDate;
    private String status; // "BORROWED", "RETURNED"

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}