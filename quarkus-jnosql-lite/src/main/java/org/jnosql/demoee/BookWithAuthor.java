package org.jnosql.demoee;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;

import java.util.Objects;

@Entity
public class BookWithAuthor {

    public static BookWithAuthor of(Book book, Author author) {
        return new BookWithAuthor(book.getId(), book.getTitle(), author);
    }

    public static BookWithAuthor of(Book book, AuthorWithBooks author) {
        return new BookWithAuthor(book.getId(), book.getTitle(), author.getAuthor());
    }

    @Id
    private String id;
    @Column
    private String title;
    @Column
    private Author author;

    /**
     * don't use it
     */
    @Deprecated
    public BookWithAuthor() {
    }

    public BookWithAuthor(String id, String title, Author author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookWithAuthor that = (BookWithAuthor) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public BookWithAuthor updateAuthor(Author author) {
        this.setAuthor(author);
        return this;
    }

    public BookWithAuthor updateTitle(String title) {
        this.setTitle(title);
        return this;
    }

    public Book getBook() {
        return Book.of(this);
    }
}
