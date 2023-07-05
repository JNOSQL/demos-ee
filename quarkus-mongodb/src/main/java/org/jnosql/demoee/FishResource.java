package org.jnosql.demoee;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.nosql.document.DocumentTemplate;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.UUID;

@Path("/fishes")
@ApplicationScoped
public class FishResource {

    @Inject
    private DocumentTemplate template;

    @GET
    public Fish hello() {
        Fish fish = new Fish();
        fish.setName(UUID.randomUUID().toString());
        fish.setColor("Blue");
        Fish insert = template.insert(fish);
        return insert;
    }
}
