package main.java.guru.springframework.spring_6_webapp.bootstrap;

import java.util.concurrent.Flow.Publisher;

import main.java.guru.springframework.spring_6_webapp.domain.Author;
import main.java.guru.springframework.spring_6_webapp.domain.Book;
import main.java.guru.springframework.spring_6_webapp.repositories.AuthorRepository;
import main.java.guru.springframework.spring_6_webapp.repositories.BookRepository;
import main.java.guru.springframework.spring_6_webapp.repositories.PublisherRepository;

@Component
public class BootstrapData implements CommandLineRunner{
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("12123123");

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);

        Author red = new Author();
        red.setFirstName("Red");
        red.getLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("123241232");

        Author redSaved = authorRepository.save(red);
        Book noEJBSaved = authorRepository.save(noEJB);

        // Association
        ericSaved.getBooks().add(ddd);
        redSaved.getBooks().add(noEJBSaved);


        Publisher publisher = new Publisher();
        publisher.setPublisherName("My Publisher");
        publisher.setAddress("123 Main");
        Publisher savedPublisher = publisherRepository.saved(publisher);

        dddSaved.setPublisher(savedPublisher);
        noEJBSaved.setPublisher(savedPublisher);

        //persistant
        authorRepository.save(ericSaved);
        authorRepository.save(redSaved);
        bookRepository.save(dddSaved);
        bookRepository.save(noEJBSaved);

        System.out.println("In Bootsrap");
        System.out.println("Author Count"+ authorRepository.count());
        System.out.println("Book count"+ bookRepository.count());

    }
}
