package com.example.ls4_1_author.Service;

import com.example.ls4_1_author.Model.DTO.BookRequest;
import com.example.ls4_1_author.Model.DTO.BorrowResponseDTO;
import com.example.ls4_1_author.Model.Entity.Author;
import com.example.ls4_1_author.Model.Entity.Book;
import com.example.ls4_1_author.Model.Entity.BorrowTicket;
import com.example.ls4_1_author.Repository.AuthorRepository;
import com.example.ls4_1_author.Repository.BookRepository;
import com.example.ls4_1_author.Repository.BroowTicketReoisitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository br;

    @Autowired
    private AuthorRepository ar;

    @Autowired
    private BroowTicketReoisitory btr;

    // --- NGHIỆP VỤ MƯỢN SÁCH ---
    @Transactional
    public BorrowResponseDTO borrowBook(Long bookId, String studentName) {
        // 1. Kiểm tra sách có tồn tại không
        Book book = br.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Lỗi: Sách không tồn tại!"));

        // 2. Kiểm tra xem sách có đang được mượn hay không (Status = BORROWED)
        boolean isAlreadyBorrowed = btr.findByBookIdAndStatus(bookId, "BORROWED").isPresent();
        if (isAlreadyBorrowed) {
            throw new RuntimeException("Sách này hiện đang được mượn, vui lòng chọn sách khác!");
        }

        // 3. Tạo mới phiếu mượn (BorrowTicket)
        BorrowTicket ticket = new BorrowTicket();
        ticket.setStudentName(studentName);
        ticket.setBook(book);
        ticket.setBorrowDate(LocalDate.now());
        ticket.setStatus("BORROWED");

        btr.save(ticket);

        // 4. Trả về thông tin gọn đẹp qua DTO
        return new BorrowResponseDTO(
                studentName,
                book.getTitle(),
                book.getAuthor().getName(),
                ticket.getBorrowDate()
        );
    }

    // --- CÁC NGHIỆP VỤ CƠ BẢN ---
    public ResponseEntity<?> addBook(BookRequest bookRequest) {
        Author author = ar.findById(bookRequest.getAuthorId()).orElse(null);
        if (author == null) {
            return new ResponseEntity<>("Tác giả không tồn tại", HttpStatus.BAD_REQUEST);
        } else {
            Book newBook = new Book();
            newBook.setAuthor(author);
            newBook.setTitle(bookRequest.getTitle());
            newBook.setPrice(bookRequest.getPrice());
            Book savedBook = br.save(newBook);
            return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
        }
    }

    public List<Book> findAllBooks() {
        return br.findAll();
    }

    public Book findBookById(Long id) {
        return br.findById(id).orElse(null);
    }

    // --- CÁC TRUY VẤN TÙY BIẾN ---
    public List<Book> searchByTitle(String title) {
        return br.findByTitleContaining(title);
    }

    public List<Book> getBooksHighPrice() {
        return br.findBookByPriceAvg();
    }

    public List<Object[]> getStatisticsByAuthor() {
        return br.countBooksByAuthorNative();
    }
}