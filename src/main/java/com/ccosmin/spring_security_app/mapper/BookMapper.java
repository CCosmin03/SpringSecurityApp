package com.ccosmin.spring_security_app.mapper;

import com.ccosmin.spring_security_app.dto.BookDto;
import com.ccosmin.spring_security_app.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookMapper {

    public BookDto bookEntityToDto(Book book) {
        if (book == null) return null;
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .price(book.getPrice())
                .stock(book.getStock())
                .build();
    }

    public Book bookDtoToEntity(BookDto bookDto) {
        if (bookDto == null) return null;
        return Book.builder()
                .id(bookDto.getId())
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .price(bookDto.getPrice())
                .stock(bookDto.getStock())
                .build();
    }

    public List<BookDto> bookListEntityToDto(List<Book> books) {
        return books.stream().map(this::bookEntityToDto).toList();
    }

    public List<Book> bookListDtoToEntity(List<BookDto> bookDtos) {
        return bookDtos.stream().map(this::bookDtoToEntity).toList();
    }
}
