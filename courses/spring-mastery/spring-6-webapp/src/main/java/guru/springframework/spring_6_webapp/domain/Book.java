package main.java.guru.springframework.spring_6_webapp.domain;

import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String title;
    private String isbn;

    @ManyToMany(mappedBy="authors")
    @JoinTable(name="author_books", joinColums=@joinColums(name="book_id"), inverseJoinColumn=@JoinColumn(name="author_id"))
    private Set<Author> authors;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
}
