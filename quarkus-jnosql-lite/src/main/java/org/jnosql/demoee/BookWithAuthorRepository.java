package org.jnosql.demoee;

import jakarta.data.page.Pageable;
import jakarta.data.repository.*;
import jakarta.ws.rs.QueryParam;

import java.util.stream.Stream;

@Repository
public interface BookWithAuthorRepository extends PageableRepository<BookWithAuthor, String> {
    @Query("select * from BookWithAuthor where author._id = @authorId")
    Stream<BookWithAuthor> listBooksByAuthorId(@Param("authorId") String id);

    Stream<BookWithAuthor> findByTitleLike(String title, Pageable pageable);
}
