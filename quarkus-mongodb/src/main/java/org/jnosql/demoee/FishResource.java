package org.jnosql.demoee;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/fishes")
@ApplicationScoped
public class FishResource {

    @Inject
    private FishService service;

    @GET
    @Path("{id}")
    public Fish findId(@PathParam("id") String id) {
        return service.findById(id)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }

    @GET
    @Path("random")
    public Fish random(){
       return service.random();

    }

    @GET
    public List<Fish> findAll(){
       return this.service.findAll();
    }

    @POST
    public Fish insert(Fish fish) {
        fish.id = null;
        return this.service.insert(fish);
    }

    @PUT
    @Path("{id}")
    public Fish update(@PathParam("id") String id, Fish fish){
       return this.service.update(id, fish)
               .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") String id){
        this.service.delete(id);
    }
}
