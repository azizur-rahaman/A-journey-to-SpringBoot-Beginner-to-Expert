package aziz.springframework.spring6webapp.services;

import org.springframework.stereotype.Service;

import aziz.springframework.spring6webapp.domain.Book;
import aziz.springframework.spring6webapp.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {
    
    private final BookRepository bookRepository;

    BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Iterable<Book> findAll(){
        return bookRepository.findAll(); // Placeholder return statement    
    }
}
