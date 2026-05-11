package com.example.ls4_1_author.Model.DTO;

public class BorrowResponseDTO {
    private String strudentId;
    private String bookTitle;
    public String authorName;
    private String borrowDate;

    public BorrowResponseDTO() {
    }

    public BorrowResponseDTO(String strudentId, String bookTitle, String authorName, String borrowDate) {
        this.strudentId = strudentId;
        this.bookTitle = bookTitle;
        this.authorName = authorName;
        this.borrowDate = borrowDate;
    }

    public String getStrudentId() {
        return strudentId;
    }

    public void setStrudentId(String strudentId) {
        this.strudentId = strudentId;
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

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }
}