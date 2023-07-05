package org.soujava;

import jakarta.data.repository.PageableRepository;
import jakarta.data.repository.Repository;


@Repository
public interface AnimalRepository extends PageableRepository<Animal, String> {
}