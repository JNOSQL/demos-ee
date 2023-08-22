package org.jnosql.demoee;

import com.github.javafaker.Faker;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class BookResourceTest {

    static final Faker faker = new Faker();

    @Inject
    Bookstore bookstore;
    @Inject
    AuthorWithBooksRepository authorWithBooksRepository;
    @Inject
    BookWithAuthorRepository bookWithAuthorRepository;

    @BeforeEach
    @AfterEach
    void cleanUpDatabase() {
        authorWithBooksRepository.deleteAll();
        bookWithAuthorRepository.deleteAll();
    }

    @Test
    public void postNewBook() {

        record NewBook(String title, Author author) {
        }

        var book = faker.book();

        var authorWithBooks = bookstore.save(AuthorWithBooks.of(Author.of(book.author())));

        NewBook request = new NewBook(book.title(), authorWithBooks.getAuthor());

        given().log().everything().
                contentType(ContentType.JSON).
                body(request).
                when().
                post("/books").
                then().log().everything().
                statusCode(HttpStatus.SC_OK).
                body(
                        "id", notNullValue(),
                        "title", is(request.title()),
                        "author.id", is(request.author().getId()),
                        "author.name", is(request.author().getName())
                );
    }

    @Test
    public void getAllBooks() {
        var book = faker.book();

        Book book1 = Book.of(book.title());
        Book book2 = Book.of(book.title());
        Book book3 = Book.of(book.title());
        var authorWithBooks1 = bookstore.save(AuthorWithBooks.of(book.author(),
                book1, book2, book3));

        Book book4 = Book.of(book.title());
        Book book5 = Book.of(book.title());
        var authorWithBooks2 = bookstore.save(AuthorWithBooks.of(book.author(),
                book4, book5));

        Book book6 = Book.of(book.title());
        var authorWithBooks3 = bookstore.save(AuthorWithBooks.of(book.author(),
                book6));


        BookWithAuthor[] books = new BookWithAuthor[]{
                BookWithAuthor.of(book1,authorWithBooks1),
                BookWithAuthor.of(book2,authorWithBooks1),
                BookWithAuthor.of(book3,authorWithBooks1),
                BookWithAuthor.of(book4,authorWithBooks2),
                BookWithAuthor.of(book5,authorWithBooks2),
                BookWithAuthor.of(book6,authorWithBooks3)};

        List<BookWithAuthor> persistedAuthors = given().
                log().everything().
                accept(ContentType.JSON).
                when().
                get("/books").
                then().
                log().everything().
                statusCode(HttpStatus.SC_OK).
                body("$", hasSize(books.length)).
                and().
                extract().as(new TypeRef<List<BookWithAuthor>>() {
                });

        assertThat(persistedAuthors, hasItems(books));

    }

}