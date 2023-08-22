package org.jnosql.demoee;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Book {

    public static Book of(String title) {
        return new Book(UUID.randomUUID().toString(), title);
    }

    public static Book of(BookWithAuthor bookWithAuthor) {
        return new Book(bookWithAuthor.getId(), bookWithAuthor.getTitle());
    }

    @Id
    private String id;
    @Column
    private String title;

    /**
     * Don't use it
     */
    @Deprecated
    public Book() {
    }

    public Book(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Book updateTitle(String title) {
        this.setTitle(title);
        return this;
    }
}
