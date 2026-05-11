package com.example.ls4_1_author.Model.DTO;

import java.time.LocalDate;

public class BorrowResponseDTO {
    private String strudentName;
    private String bookTitle;
    public String authorName;
    private LocalDate borrowDate;

    public BorrowResponseDTO() {
    }

    public BorrowResponseDTO(String strudentName, String bookTitle, String authorName,LocalDate borrowDate) {
        this.strudentName = strudentName;
        this.bookTitle = bookTitle;
        this.authorName = authorName;
        this.borrowDate = borrowDate;
    }

    public String getStrudentName() {
        return strudentName;
    }

    public void setStrudentName(String strudentName) {
        this.strudentName = strudentName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }
}