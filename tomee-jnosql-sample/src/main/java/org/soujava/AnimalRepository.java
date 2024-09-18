package org.soujava;

import jakarta.data.repository.BasicRepository;
import jakarta.data.repository.Repository;


@Repository
public interface AnimalRepository extends BasicRepository<Animal, String> {
}
