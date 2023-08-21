package org.jnosql.demoee;

import jakarta.data.repository.PageableRepository;
import jakarta.data.repository.Repository;

@Repository
public interface DogRepository extends PageableRepository<Dog, String> {
}
