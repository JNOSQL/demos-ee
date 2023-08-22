package org.jnosql.demoee;

import jakarta.data.repository.Pageable;
import jakarta.data.repository.PageableRepository;
import jakarta.data.repository.Repository;

import java.util.stream.Stream;

@Repository
public interface AuthorWithBooksRepository extends PageableRepository<AuthorWithBooks, String> {
    Stream<AuthorWithBooks> findByName(String name, Pageable pageable);
}
