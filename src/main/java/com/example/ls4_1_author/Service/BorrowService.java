package com.example.ls4_1_author.Service;


import com.example.ls4_1_author.Model.DTO.BorrowResponseDTO;
import com.example.ls4_1_author.Model.Entity.Book;
import com.example.ls4_1_author.Model.Entity.BorrowTicket;
import com.example.ls4_1_author.Repository.BookRepository;
import com.example.ls4_1_author.Repository.BroowTicketReoisitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class BorrowService {
    @Autowired
    private BroowTicketReoisitory btr;
    @Autowired
    private BookRepository br;

    @Transactional
    public BorrowResponseDTO borrowBook(Long bookId, String studentName) {
        // 1. Kiểm tra sách tồn tại
        Book book = br.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Lỗi 404: Không tìm thấy sách"));

        // 2. Kiểm tra sách đã được mượn chưa
        if (btr.findByBookIdAndStatus(bookId, "BORROWED").isPresent()) {
            throw new RuntimeException("Sách này hiện đang được mượn, vui lòng chọn cuốn khác!");
        }

        // 3. Tạo phiếu mượn
        BorrowTicket ticket = new BorrowTicket();
        ticket.setStudentName(studentName);
        ticket.setBook(book);
        ticket.setBorrowDate(LocalDate.now());
        ticket.setStatus("BORROWED");

        btr.save(ticket);

        // 4. Trả về DTO
        return new BorrowResponseDTO(
                ticket.getStudentName(),
                book.getTitle(),
                book.getAuthor().getName(),
                ticket.getBorrowDate()
        );
    }
}