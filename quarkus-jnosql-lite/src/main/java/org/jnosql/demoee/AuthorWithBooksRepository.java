package org.jnosql.demoee;

import jakarta.data.page.PageRequest;
import jakarta.data.repository.BasicRepository;
import jakarta.data.repository.Repository;

import java.util.stream.Stream;

@Repository
public interface AuthorWithBooksRepository extends BasicRepository<AuthorWithBooks, String> {
    Stream<AuthorWithBooks> findByName(String name, PageRequest pageable);
}
