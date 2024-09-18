package org.jnosql.demoee;

import jakarta.data.page.PageRequest;
import jakarta.data.repository.*;

import java.util.stream.Stream;

@Repository
public interface BookWithAuthorRepository extends BasicRepository<BookWithAuthor, String> {
    @Query("select * from BookWithAuthor where author._id = @authorId")
    Stream<BookWithAuthor> listBooksByAuthorId(@Param("authorId") String id);

    Stream<BookWithAuthor> findByTitleLike(String title, PageRequest pageable);
}
