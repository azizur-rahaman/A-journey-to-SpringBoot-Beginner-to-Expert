package aziz.springframework.spring6webapp.bootstrap;
import aziz.springframework.spring6webapp.domain.Author;
import aziz.springframework.spring6webapp.domain.Book;
import aziz.springframework.spring6webapp.repositories.*;

import org.springframework.boot.CommandLineRunner;

public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Sam");

        Book ddd = new Book();
        ddd.setTitle("Domain driven design");
        ddd.setIsbn("123456789");

        Author ericsaved = authorRepository.save(eric);
        Book dddSave = bookRepository.save(ddd);
    }
}
