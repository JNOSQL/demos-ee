package org.jnosql.demoee;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.nosql.document.DocumentTemplate;
import jakarta.ws.rs.*;

import java.util.List;
import java.util.UUID;

@Path("/fishes")
@ApplicationScoped
public class FishResource {

    @Inject
    private FishService service;

    @GET
    public Fish hello() {
        Fish fish = new Fish();
        fish.setName(UUID.randomUUID().toString());
        fish.setColor("Blue");
        return service.insert(fish);
    }

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
       return this.service.update(id, fish);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") String id){
        this.service.delete(id);
    }
}
