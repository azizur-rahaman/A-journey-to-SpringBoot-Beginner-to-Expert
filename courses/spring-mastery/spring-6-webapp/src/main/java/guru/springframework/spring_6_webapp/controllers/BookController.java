package main.java.guru.springframework.spring_6_webapp.controllers;

import main.java.guru.springframework.spring_6_webapp.services.BookService;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    public String getBooks(Model Model) {
        model.addAttribute("books", bookService.findAll());

        return "books";
    }
    
}
