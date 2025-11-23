package main.java.guru.springframework.spring_6_webapp.services;

import main.java.guru.springframework.spring_6_webapp.domain.Book;

public interface BookService {
    Iterable<Book> findAll();    
}