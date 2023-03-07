package org.eclipse.jnosql.demoee.liberty.mongodb;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/persons")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class PersonController {
    @Inject
    PersonRepository personRepository;


    @GET
    public List<Person> listAll(){
        Stream<Person> all = personRepository.findAll();
        return all.collect(Collectors.toList());
    }

    @POST
    public void addPerson(Person person){
        personRepository.save(person);
    }

}
