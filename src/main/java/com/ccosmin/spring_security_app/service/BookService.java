package com.ccosmin.spring_security_app.service;

import java.util.List;
import com.ccosmin.spring_security_app.dto.BookDto;
import com.ccosmin.spring_security_app.model.Book;

public interface BookService {

    List<BookDto> getAllBooks();

    BookDto getBookById(Long id);

    BookDto createBook(Book book);

    BookDto updateBook(Book book);

    void deleteBook(Long id);
}
