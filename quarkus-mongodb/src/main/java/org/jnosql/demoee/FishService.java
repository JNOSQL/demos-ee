package org.jnosql.demoee;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.nosql.document.DocumentTemplate;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class FishService {

    @Inject
    private DocumentTemplate template;


    public List<Fish> findAll(){
        return template.select(Fish.class).result();
    }

    public Fish insert(Fish fish) {
        return this.template.insert(fish);
    }

     public void delete(String id) {
        this.template.delete(Fish.class, new ObjectId(id));
     }

     public Fish update(String id, Fish fish){
        Optional<Fish> optional = this.template.find(Fish.class, new ObjectId(id));
        return optional.map(f -> {
            f.name = fish.name;
            f.color = fish.color;
            return this.template.update(f);
        }).orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
        //you should have a proper exception, only for demo proposes
    }




}
