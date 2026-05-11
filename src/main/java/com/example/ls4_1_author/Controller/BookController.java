package com.example.ls4_1_author.Controller;

import com.example.ls4_1_author.Model.DTO.BookRequest;
import com.example.ls4_1_author.Model.Entity.Book;
import com.example.ls4_1_author.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
public class BookController {
    @Autowired
    private BookService bs;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bs.findAllBooks(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody BookRequest br) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bs.addBook(br));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        Book book = bs.findBookById(id);
        if(book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        }
    }
}