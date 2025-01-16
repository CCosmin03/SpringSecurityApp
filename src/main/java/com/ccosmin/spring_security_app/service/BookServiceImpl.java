package com.ccosmin.spring_security_app.service;

import com.ccosmin.spring_security_app.dto.BookDto;
import com.ccosmin.spring_security_app.mapper.BookMapper;
import com.ccosmin.spring_security_app.model.Book;
import com.ccosmin.spring_security_app.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookDto> getAllBooks() {
        return bookMapper.bookListEntityToDto(bookRepository.findAll());
    }

    @Override
    public BookDto getBookById(Long id) {
        return bookMapper.bookEntityToDto(bookRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public BookDto createBook(Book book) {
        return bookMapper.bookEntityToDto(bookRepository.save(book));
    }

    @Override
    @Transactional
    public BookDto updateBook(Book book) {
        return bookMapper.bookEntityToDto(bookRepository.save(book));
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
