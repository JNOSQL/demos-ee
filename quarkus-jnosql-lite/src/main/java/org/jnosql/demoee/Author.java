package org.jnosql.demoee;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Author {

    public static Author of(String name){
        Author author = new Author();
        author.setId(UUID.randomUUID().toString());
        author.setName(name);
        return author;
    }

    public static Author of(AuthorWithBooks authorWithBooks){
        Author author = new Author();
        author.setId(authorWithBooks.getId());
        author.setName(authorWithBooks.getName());
        return author;
    }

    @Id
    private String id;
    @Column
    private String name;

    /**
     * Don't use it
     */
    @Deprecated
    public Author(){}

    public Author(String id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
