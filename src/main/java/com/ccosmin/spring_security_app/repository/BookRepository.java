package com.ccosmin.spring_security_app.repository;


import com.ccosmin.spring_security_app.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {}
