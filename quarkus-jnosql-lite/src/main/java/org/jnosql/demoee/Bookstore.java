package org.jnosql.demoee;

import jakarta.data.Order;
import jakarta.data.page.PageRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@ApplicationScoped
public class Bookstore {

    private final AuthorWithBooksRepository authorWithBooksRepository;
    private final BookWithAuthorRepository bookWithAuthorRepository;

    @Inject
    public Bookstore(AuthorWithBooksRepository authorWithBooksRepository,
                     BookWithAuthorRepository bookWithAuthorRepository) {
        this.authorWithBooksRepository = authorWithBooksRepository;
        this.bookWithAuthorRepository = bookWithAuthorRepository;
    }

    public AuthorWithBooks save(AuthorWithBooks author) {

        authorWithBooksRepository.save(author);

        bookWithAuthorRepository.listBooksByAuthorId(author.getId())
                .map(BookWithAuthor::getId)
                .forEach(bookWithAuthorRepository::deleteById);

        author.getBooks().stream()
                .map(book -> BookWithAuthor.of(book, author))
                .forEach(bookWithAuthorRepository::save);

        return author;
    }

    public Stream<AuthorWithBooks> findAuthorsByName(String name, PageRequest pageable) {
        return authorWithBooksRepository.findByName(name, pageable);
    }

    public Stream<AuthorWithBooks> listAuthors(PageRequest pageable) {
        return authorWithBooksRepository.findAll(pageable, Order.by()).stream();
    }

    public Optional<AuthorWithBooks> findAuthorById(String authorId) {
        return authorWithBooksRepository.findById(authorId);
    }

    public BookWithAuthor save(BookWithAuthor bookWithAuthor) {
        bookWithAuthorRepository.save(bookWithAuthor);

        this.authorWithBooksRepository.findById(bookWithAuthor.getAuthor().getId())
                .map(author-> author.add(bookWithAuthor.getBook()))
                .ifPresent(this.authorWithBooksRepository::save);

        return bookWithAuthor;
    }

    public Stream<BookWithAuthor> findBooksByTitle(String title, PageRequest pageable) {
        return bookWithAuthorRepository.findByTitleLike(title, pageable);
    }

    public Stream<BookWithAuthor> listBooks(PageRequest pageable) {
        return bookWithAuthorRepository.findAll(pageable, Order.by()).stream();
    }
}
