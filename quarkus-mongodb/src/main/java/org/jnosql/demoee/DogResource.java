package org.jnosql.demoee;

import com.github.javafaker.Faker;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Path("/dogs")
@ApplicationScoped
public class DogResource {

    @Inject
    private DogRepository repository;

    @GET
    @Path("{id}")
    public Dog findId(@PathParam("id") String id) {
        return repository.findById(id)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }

    @GET
    @Path("random")
    public List<Dog> random(){
        var faker = new Faker();
        List<Dog> dogs = new LinkedList<>();
        for (int index = 0; index < 200; index++) {
            var dog = new Dog();
            dog.id = faker.idNumber().valid();
            dog.name = faker.name().fullName();
            dog.color = faker.color().name();
            dogs.add(repository.save(dog));
        }
       return dogs;
    }

    @GET
    public List<Dog> findAll(){
       return this.repository.findAll().toList();
    }

    @POST
    public Dog insert(Dog dog) {
        dog.id = UUID.randomUUID().toString();
        return this.repository.save(dog);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") String id){
        this.repository.deleteById(id);
    }
}
