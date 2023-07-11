package org.jnosql.demoee;

import com.github.javafaker.Faker;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.nosql.document.DocumentTemplate;
import org.apache.http.HttpStatus;
import org.jnosql.demoee.DevelopersResource.NewDeveloperRequest;
import org.jnosql.demoee.DevelopersResource.UpdateDeveloperRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
class DevelopersResourceTest {

    Faker faker = new Faker();

    @Inject
    DocumentTemplate template;

    @BeforeEach
    @AfterEach
    public void cleanDatabase() {
        template.delete(Developer.class).execute();
    }

    private Developer createRandomDeveloper() {
        return Developer.newDeveloper(
                faker.name().fullName(),
                LocalDate.ofInstant(faker.date().birthday().toInstant(), ZoneId.systemDefault())
        );
    }

    @Test
    void shouldListAllDevelopers() {
        given()
                .when().get("/developers")
                .then()
                .statusCode(200)
                .body("size()", is(0));

        var expectedResults = List.of(createRandomDeveloper(), createRandomDeveloper());

        template.insert(expectedResults);

        var results = given()
                .when().get("/developers")
                .then()
                .statusCode(200)
                .extract().body().as(new TypeRef<List<Developer>>() {
                });

        assertThat(results).containsAll(expectedResults);

    }

    @Test
    void shouldFindDevelopersByName() {

        given()
                .params("name", "M")
                .when().get("/developers")
                .then()
                .statusCode(200)
                .body("size()", is(0));

        given()
                .when().get("/developers")
                .then()
                .statusCode(200)
                .body("size()", is(0));

        var maximillianArruda = Developer.newDeveloper(
                "Maximillian Arruda",
                LocalDate.ofInstant(faker.date().birthday().toInstant(), ZoneId.systemDefault())
        );

        var johnDoe = Developer.newDeveloper(
                "John Doe",
                LocalDate.ofInstant(faker.date().birthday().toInstant(), ZoneId.systemDefault())
        );

        List<Developer> persistedDevelopers = List.of(maximillianArruda, johnDoe);

        template.insert(persistedDevelopers);

        var result = given()
                .params("name", "M")
                .when().get("/developers")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(new TypeRef<List<Developer>>() {});

        assertThat(result).containsAll(List.of(maximillianArruda));


        var result2 = given()
                .when().get("/developers")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(new TypeRef<List<Developer>>() {});
        assertThat(result2).containsAll(persistedDevelopers);

    }

    @Test
    void shouldAddDeveloper() {

        var developer = new NewDeveloperRequest(
                faker.name().fullName(),
                LocalDate.ofInstant(faker.date().birthday().toInstant(), ZoneId.systemDefault())
        );

        var persistedDeveloper = given()
                .contentType(ContentType.JSON)
                .body(developer)
                .when()
                .post("/developers")
                .then()
                .statusCode(200)
                .body(
                        "id", notNullValue(),
                        "name", is(developer.name()),
                        "birthday", is(developer.birthday().toString())
                )
                .extract()
                .body().as(Developer.class);

        var loadedDeveloper = given()
                .when()
                .get("/developers/{id}", Map.of("id", persistedDeveloper.id()))
                .then()
                .statusCode(200)
                .body(
                        "id", notNullValue(),
                        "name", is(developer.name()),
                        "birthday", is(developer.birthday().toString())
                )
                .extract()
                .body().as(Developer.class);

        assertThat(loadedDeveloper).isEqualTo(persistedDeveloper);

    }

    @Test
    void shouldGetDeveloper() {

        var expectedDeveloper = createRandomDeveloper();
        template.insert(expectedDeveloper);

        var loadedDeveloper = given()
                .when()
                .get("/developers/{id}", Map.of("id", expectedDeveloper.id()))
                .then()
                .statusCode(200)
                .extract()
                .body().as(Developer.class);

        assertThat(loadedDeveloper).isEqualTo(expectedDeveloper);

    }

    @Test
    void shouldUpdateDeveloper() {
        var persistedDeveloper = createRandomDeveloper();
        template.insert(persistedDeveloper);

        var expectedDeveloper = persistedDeveloper.update(faker.name().fullName(),
                LocalDate.ofInstant(faker.date().birthday().toInstant(), ZoneId.systemDefault()));

        var payload = new UpdateDeveloperRequest(expectedDeveloper.name(), expectedDeveloper.birthday());

        var updatedDeveloper = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .put("/developers/{id}", Map.of("id", persistedDeveloper.id()))
                .then()
                .statusCode(200)
                .extract()
                .body().as(Developer.class);

        assertThat(updatedDeveloper).isEqualTo(expectedDeveloper);
    }


    @Test
    void shouldDeleteDeveloper() {
        var persistedDeveloper = createRandomDeveloper();
        template.insert(persistedDeveloper);

        given()
                .when()
                .delete("/developers/{id}", Map.of("id", persistedDeveloper.id()))
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

        assertThat(template.select(Developer.class).result()).hasSize(0);
    }
}