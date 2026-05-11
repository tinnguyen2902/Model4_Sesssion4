package com.example.ls4_1_author.Service;

import com.example.ls4_1_author.Model.DTO.BookRequest;
import com.example.ls4_1_author.Model.Entity.Author;
import com.example.ls4_1_author.Model.Entity.Book;
import com.example.ls4_1_author.Repository.AuthorRepository;
import com.example.ls4_1_author.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookService {
    @Autowired

private BookRepository br;
    @Autowired
    private AuthorRepository ar;
    public ResponseEntity<?> addBook(BookRequest bookRequest) {
        Author author = ar.findById(bookRequest.getAuthorId()).orElse(null);
        if (author == null) {
            return new ResponseEntity<>("Tác giả không tồn tại",HttpStatus.BAD_REQUEST);
        } else {
            Book newBook = new Book();
            newBook.setAuthor(author);
            newBook.setTitle(bookRequest.getTitle());
            newBook.setPrice(bookRequest.getPrice());
            Book savedBook = br.save(newBook);
            return  new ResponseEntity<>(savedBook, HttpStatus.CREATED);
        }
    }
    public List<Book> findAllBooks() {
        return  br.findAll();
    }

    public Book findBookById(Long id) {
        return br.findById(id).orElse(null);
    }
}