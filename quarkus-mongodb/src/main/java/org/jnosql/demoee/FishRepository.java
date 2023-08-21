package org.jnosql.demoee;

import jakarta.data.repository.PageableRepository;
import jakarta.data.repository.Repository;

@Repository
public interface FishRepository extends PageableRepository<Fish, String> {
}
