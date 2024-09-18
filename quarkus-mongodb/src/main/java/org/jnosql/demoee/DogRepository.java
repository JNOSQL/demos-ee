package org.jnosql.demoee;


import jakarta.data.repository.BasicRepository;
import jakarta.data.repository.Repository;

@Repository
public interface DogRepository extends BasicRepository<Dog, String> {
}
