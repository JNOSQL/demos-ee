package org.jnosql.demoee;

public record BookResponse(String id, String title, AuthorResponse author) {
    public static BookResponse of(BookWithAuthor book) {
        return new BookResponse(book.getId(), book.getTitle(), AuthorResponse.of(book.getAuthor()));
    }
}
