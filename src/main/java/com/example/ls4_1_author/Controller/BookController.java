package com.example.ls4_1_author.Controller;

import com.example.ls4_1_author.Model.DTO.BookRequest;
import com.example.ls4_1_author.Model.DTO.BorrowResponseDTO;
import com.example.ls4_1_author.Model.Entity.Book;
import com.example.ls4_1_author.Model.Entity.BorrowTicket;
import com.example.ls4_1_author.Repository.AuthorRepository;
import com.example.ls4_1_author.Repository.BroowTicketReoisitory;
import com.example.ls4_1_author.Service.BookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/searchByTitle")
    public ResponseEntity<List<Book>> searchByTitle(@RequestParam String keyword) {
        return new ResponseEntity<>(bs.searchByTitle(keyword), HttpStatus.OK);
    }

    @GetMapping("/getBookHightPrice")
    public ResponseEntity<List<Book>> getHighPriceBooks() {
        return new ResponseEntity<>(bs.getBooksHighPrice(), HttpStatus.OK);
    }

    @GetMapping("/statisticsByAuthor")
    public ResponseEntity<List<Object[]>> getAuthorStats() {
        return new ResponseEntity<>(bs.getStatisticsByAuthor(), HttpStatus.OK);
    }
    @PostMapping("/{bookId}/borrow") // Nên thêm /borrow để tránh trùng với các POST khác
    public ResponseEntity<?> borrowBook(
            @PathVariable Long bookId,
            @RequestParam String studentName) {
        try {
            // Gọi sang Service để xử lý
            BorrowResponseDTO response = bs.borrowBook(bookId, studentName);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}