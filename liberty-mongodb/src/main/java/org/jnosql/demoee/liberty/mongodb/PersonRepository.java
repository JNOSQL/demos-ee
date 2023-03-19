package org.jnosql.demoee.liberty.mongodb;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, String> {
}
