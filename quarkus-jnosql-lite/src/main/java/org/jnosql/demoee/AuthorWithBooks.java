package org.jnosql.demoee;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
public class AuthorWithBooks {

    public static AuthorWithBooks of(String name, Book... books) {
        return of(Author.of(name), books);
    }

    public static AuthorWithBooks of(Author author, Book... books) {
        return new AuthorWithBooks(author.getId(), author.getName(), Arrays.asList(books));
    }

    @Id
    private String id;
    @Column
    private String name;
    @Column
    private List<Book> books = new LinkedList<>();

    /**
     * don't use it
     */
    @Deprecated
    public AuthorWithBooks() {
    }

    public AuthorWithBooks(String id, String name, List<Book> books) {
        this.id = id;
        this.name = name;
        this.books = books;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorWithBooks that = (AuthorWithBooks) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public AuthorWithBooks updateName(String name) {
        this.setName(name);
        return this;
    }

    public AuthorWithBooks add(Book book) {
        this.books.add(book);
        return this;
    }

    public Author getAuthor() {
        return Author.of(this);
    }
}
