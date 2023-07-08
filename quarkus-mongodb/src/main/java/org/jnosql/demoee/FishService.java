package org.jnosql.demoee;


import com.github.javafaker.Animal;
import com.github.javafaker.Faker;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.nosql.document.DocumentTemplate;


import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class FishService {

    @Inject
    private DocumentTemplate template;

    private final Faker faker = new Faker();


    public List<Fish> findAll() {
        return template.select(Fish.class).result();
    }

    public Fish insert(Fish fish) {
        fish.id = UUID.randomUUID().toString();
        return this.template.insert(fish);
    }

    public void delete(String id) {
        this.template.delete(Fish.class, id);
    }

    public Optional<Fish> findById(String id){
        return this.template.find(Fish.class, id);
    }

    public Fish random() {
        Fish fish = new Fish();
        Animal animal = faker.animal();
        fish.id = UUID.randomUUID().toString();
        fish.name = animal.name();
        fish.color = faker.color().name();
        return this.template.insert(fish);
    }

    public Optional<Fish> update(String id, Fish fish) {
        Optional<Fish> optional = this.template.find(Fish.class, new ObjectId(id));
        return optional.map(f -> {
            f.name = fish.name;
            f.color = fish.color;
            return this.template.update(f);
        });

    }

}
