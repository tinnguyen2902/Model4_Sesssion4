package com.example.ls4_1_author.Repository;

import com.example.ls4_1_author.Model.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    // tìm sách theo tiêu đề
    List<Book> findByTitleContaining(String title);
    //lấy sách có giá > tb
    @Query("SELECT b FROM Book b WHERE b.price > (SELECT AVG(b2.price) FROM Book b2)")
    List<Book> findBookByPriceAvg();

    // thống kê sách của mỗi tác giả
    @Query(value = "SELECT a.name, COUNT(b.id) as book_count " +
            "FROM authors a LEFT JOIN books b ON a.id = b.author_id " +
            "GROUP BY a.id, a.name", nativeQuery = true)
    List<Object[]> countBooksByAuthorNative();

    List<Book> findBookByTitleContaining(String title);
}
