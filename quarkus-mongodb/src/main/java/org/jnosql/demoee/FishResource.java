package org.jnosql.demoee;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.nosql.document.DocumentTemplate;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.Arrays;
import java.util.UUID;

@Path("/jnosql")
@ApplicationScoped
public class PeopleResource {

    @Inject
    private DocumentTemplate template;

    @GET
    public String hello() {
        Fish fish = new Fish();
        fish.setName(UUID.randomUUID().toString());
        fish.setPhones(
                Arrays.asList(
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString()));
        Fish insert = template.insert(fish);
        return insert.getId();
    }
}
