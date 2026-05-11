package com.example.ls4_1_author.Repository;

import com.example.ls4_1_author.Model.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    // tìm sách theo tiêu đề
    List<Book> findBookByTitle(String keyword);

}
