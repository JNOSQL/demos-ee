package org.jnosql.demoee;

public record AuthorResponse(String id, String name) {
    public static AuthorResponse of(Author author) {
        return new AuthorResponse(author.getId(), author.getName());
    }
    public static AuthorResponse of(AuthorWithBooks author) {
        return new AuthorResponse(author.getId(), author.getName());
    }

}
