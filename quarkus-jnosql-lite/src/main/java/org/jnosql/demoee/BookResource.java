package org.jnosql.demoee;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Objects;

@ApplicationScoped
@Path("/books")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class BookResource implements PageableResource {

    @Inject
    Bookstore bookstore;

    @POST
    public BookResponse add(BookRequest request) {

        AuthorWithBooks authorWithBooks = bookstore.findAuthorById(request.author().id())
                .orElseThrow(() -> new WebApplicationException("", Response.Status.BAD_REQUEST));

        var book = bookstore.save(BookWithAuthor.of(Book.of(request.title()), authorWithBooks.getAuthor()));

        return BookResponse.of(book);
    }

    @GET
    public List<BookResponse> listAll(
            @QueryParam("title") String title,
            @QueryParam("orderBy") @DefaultValue("") String orderBy,
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize
    ) {

        var pageable = createPageable(orderBy, page, pageSize);

        if (Objects.nonNull(title)) {
            return bookstore.findBooksByTitle(title, pageable)
                    .map(BookResponse::of)
                    .toList();
        }

        return bookstore.listBooks(pageable)
                .map(BookWithAuthor.class::cast)
                .map(BookResponse::of).toList();
    }


}
