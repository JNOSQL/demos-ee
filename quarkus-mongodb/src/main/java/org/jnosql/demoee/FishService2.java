package org.jnosql.demoee;


import com.github.javafaker.Animal;
import com.github.javafaker.Faker;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class FishService2 {

    @Inject
    private FishRepository repository;

    private final Faker faker = new Faker();


    public List<Fish> findAll() {
        return repository.findAll().toList();
    }

    public Fish insert(Fish fish) {
        fish.id = UUID.randomUUID().toString();
        return this.repository.save(fish);
    }

    public void delete(String id) {
        this.repository.deleteById(id);
    }

    public Optional<Fish> findById(String id){
        return this.repository.findById(id);
    }

    public Fish random() {
        for (int index = 0; index < 199; index++) {

        }
        Fish fish = new Fish();
        Animal animal = faker.animal();
        fish.id = UUID.randomUUID().toString();
        fish.name = animal.name();
        fish.color = faker.color().name();
        return this.repository.save(fish);
    }

    public Optional<Fish> update(String id, Fish fish) {
        Optional<Fish> optional = this.repository.findById(id);
        return optional.map(f -> {
            f.name = fish.name;
            f.color = fish.color;
            return this.repository.save(f);
        });

    }

}
