package aziz.springframework.spring6webapp.services;

import aziz.springframework.spring6webapp.domain.Book;

public interface BookService {
    Iterable<Book> findAll();
}