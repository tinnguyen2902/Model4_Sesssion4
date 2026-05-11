package com.example.ls4_1_author.Repository;

import com.example.ls4_1_author.Model.Entity.BorrowTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BroowTicketReoisitory extends JpaRepository<BorrowTicket,Long> {
    // Tìm phiếu mượn của một cuốn sách mà chưa trả (status = BORROWED)
    Optional<BorrowTicket> findByBookIdAndStatus(Long bookId, String status);
}
