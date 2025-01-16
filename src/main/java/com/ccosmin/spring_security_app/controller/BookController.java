package com.ccosmin.spring_security_app.controller;

import com.ccosmin.spring_security_app.dto.BookDto;
import com.ccosmin.spring_security_app.mapper.BookMapper;
import com.ccosmin.spring_security_app.model.Book;
import com.ccosmin.spring_security_app.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public String viewBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books/books";
    }

    @GetMapping("/add")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public String addBookForm(Model model) {
        model.addAttribute("book", new BookDto());
        return "books/add_book";
    }

    @PostMapping("/add")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public String createBook(@ModelAttribute("book") BookDto bookDto, RedirectAttributes redirectAttributes) {
        bookService.createBook(bookMapper.bookDtoToEntity(bookDto));
        redirectAttributes.addFlashAttribute("successMessage", "Book added successfully!");
        return "redirect:/books";  // Redirect to book list after adding
    }

    @GetMapping("/edit/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public String editBookForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        return "books/edit_book";
    }
    @PostMapping("/edit/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public String updateBook(@PathVariable Long id, @ModelAttribute("book") BookDto bookDto, RedirectAttributes redirectAttributes) {
        Book existingBook = bookMapper.bookDtoToEntity(bookDto);  // Convert BookDto to Book entity
        existingBook.setId(id);
        bookService.updateBook(existingBook);  // Update the book in the service layer
        redirectAttributes.addFlashAttribute("successMessage", "Book updated successfully!");
        return "redirect:/books";
    }


    @GetMapping("/delete/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public String deleteBookForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        return "books/delete_book";
    }
    @PostMapping("/delete/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        bookService.deleteBook(id);
        redirectAttributes.addFlashAttribute("successMessage", "Book deleted successfully!");
        return "redirect:/books";
    }

}
