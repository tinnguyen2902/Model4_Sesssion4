package com.example.ls4_1_author.Model.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "authors")

public class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "varchar(100)", nullable = false)
    private String name;

  private String email;

    public Author(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public Author() {};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}