package aziz.springframework.spring6webapp.bootstrap;
import aziz.springframework.spring6webapp.domain.Author;
import aziz.springframework.spring6webapp.domain.Book;
import aziz.springframework.spring6webapp.domain.Publisher;
import aziz.springframework.spring6webapp.repositories.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if data already exists to avoid duplicate insertion
        if (authorRepository.count() > 0) {
            System.out.println("Data already exists, skipping bootstrap");
            return;
        }

        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Sam");

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("123456");

        Book ddd = new Book();
        ddd.setTitle("Domain driven design");
        ddd.setIsbn("123456789");

        // Create publisher first
        Publisher publisher = new Publisher();
        publisher.setPublisherName("My Publisher");
        publisher.setAddress("123 Main Street");
        Publisher savedPublisher = publisherRepository.save(publisher);

        // Set publisher before saving books
        ddd.setPublisher(savedPublisher);

        // Save entities first
        Author ericsaved = authorRepository.save(eric);
        Book dddSave = bookRepository.save(ddd);
        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = bookRepository.save(noEJB);

        // Establish bidirectional relationships
        ericsaved.getBooks().add(dddSave);
        dddSave.getAuthors().add(ericsaved);
        
        rodSaved.getBooks().add(noEJBSaved);
        noEJBSaved.getAuthors().add(rodSaved);

        // Save the updates (only once)
        authorRepository.save(ericsaved);
        authorRepository.save(rodSaved);
        bookRepository.save(dddSave);
        bookRepository.save(noEJBSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author count: " + authorRepository.count());
        System.out.println("Book count: " + bookRepository.count());
        System.out.println("Publisher count: " + publisherRepository.count());

        
    }
}
