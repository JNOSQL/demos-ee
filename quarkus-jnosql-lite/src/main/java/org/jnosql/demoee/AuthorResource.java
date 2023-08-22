package org.jnosql.demoee;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.Objects;

@ApplicationScoped
@Path("/authors")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class AuthorResource implements PageableResource {

    @Inject
    Bookstore bookstore;

    @POST
    public AuthorResponse add(AuthorRequest request) {
        AuthorWithBooks author = bookstore.save(AuthorWithBooks.of(request.name()));
        return AuthorResponse.of(author);
    }

    @GET
    public List<AuthorResponse> listAll(
            @QueryParam("name") String name,
            @QueryParam("orderBy") @DefaultValue("") String orderBy,
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize
    ) {

        var pageable = createPageable(orderBy, page, pageSize);

        if (Objects.nonNull(name)) {
            return bookstore.findAuthorsByName(name, pageable)
                    .map(AuthorResponse::of)
                    .toList();
        }

        return bookstore.listAuthors(pageable)
                .map(AuthorWithBooks.class::cast)
                .map(AuthorResponse::of).toList();
    }


}
