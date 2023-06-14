package br.com.thedevconf.communitylounge;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface TDC extends CrudRepository<Developer, String> {

}
