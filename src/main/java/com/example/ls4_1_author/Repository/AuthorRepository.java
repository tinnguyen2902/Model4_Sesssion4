package com.example.ls4_1_author.Repository;

import com.example.ls4_1_author.Model.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {

}