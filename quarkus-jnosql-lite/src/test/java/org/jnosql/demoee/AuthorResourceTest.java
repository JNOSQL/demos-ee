package org.jnosql.demoee;

import com.github.javafaker.Faker;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

@QuarkusTest
public class AuthorResourceTest {

    static final Faker faker = new Faker();

    @Inject
    Bookstore bookstore;
    @Inject
    AuthorWithBooksRepository authorWithBooksRepository;
    @Inject
    BookWithAuthorRepository bookWithAuthorRepository;

    @BeforeEach
    @AfterEach
    void cleanUpDatabase() throws InterruptedException {
        authorWithBooksRepository.deleteAll();
        bookWithAuthorRepository.deleteAll();
        TimeUnit.SECONDS.sleep(2);
    }

    @Test
    public void postNewAuthor() {
        record NewAuthor(String name) {
        }

        NewAuthor request = new NewAuthor(faker.name().fullName());

        given().
                contentType(ContentType.JSON).
                body(request).
                when().
                post("/authors").
                then().
                statusCode(HttpStatus.SC_OK).
                body(
                        "id", notNullValue(),
                        "name", is(request.name())
                );
    }

    @Test
    public void getAllAuthors() {

        AuthorWithBooks[] authors = new AuthorWithBooks[]{
                AuthorWithBooks.of(faker.name().fullName()),
                AuthorWithBooks.of(faker.name().fullName()),
                AuthorWithBooks.of(faker.name().fullName())};

        Arrays.stream(authors).forEach(bookstore::save);

        List<AuthorWithBooks> persistedAuthors = given().
                accept(ContentType.JSON).
                when().
                get("/authors").
                then().
                statusCode(HttpStatus.SC_OK).
                body("$", hasSize(3)).
                and().
                extract().as(new TypeRef<List<AuthorWithBooks>>() {
                });

        assertThat(persistedAuthors, hasItems(authors));

    }

}