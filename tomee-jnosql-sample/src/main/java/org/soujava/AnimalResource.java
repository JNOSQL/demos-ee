package org.soujava;



import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("animals")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class AnimalResource {

    @Inject
    private AnimalRepository repository;

    @POST
    public void create(Animal entity) {
        repository.save(entity);
    }

    @PUT
    @Path("{id}")
    public void edit(@PathParam("id") String id, Animal entity) {
        repository.save(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        repository.deleteById(id);
    }

    @GET
    @Path("{id}")
    public Animal find(@PathParam("id") String id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new WebApplicationException(Response.Status.NOT_FOUND));
    }

    @GET
    public List<Animal> findAll() {
        return repository.findAll().toList();
    }

}