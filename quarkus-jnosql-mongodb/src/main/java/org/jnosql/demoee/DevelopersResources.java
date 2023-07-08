package org.jnosql.demoee;

import jakarta.inject.Inject;
import jakarta.nosql.document.DocumentTemplate;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.List;

@Path("developers")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class DevelopersResources {

    @Inject
    DocumentTemplate template;

    @GET
    public List<Developer> listAll() {
        return template.select(Developer.class).result();
    }

    @Path("/byName")
    @GET
    public List<Developer> findByName(@QueryParam("name") String name) {
        return template.select(Developer.class)
                .where("name")
                .like(name)
                .result();
    }

    public record NewDeveloperRequest(String name, LocalDate birthday) {
    }

    @POST
    public Developer add(NewDeveloperRequest request) {

        var newDeveloper = Developer.newDeveloper(request.name(), request.birthday());

        return template.insert(newDeveloper);
    }

    @Path("{id}")
    @GET
    public Developer getDeveloper(@PathParam("id") String id) {
        return template.find(Developer.class, id)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }

    @Path("{id}")
    @DELETE
    public void deleteDeveloper(@PathParam("id") String id) {
        template.delete(Developer.class,id);
    }

}
