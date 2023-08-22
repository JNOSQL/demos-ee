package org.jnosql.demoee;

public record BookRequest(String title, AuthorResponse author) {
}
