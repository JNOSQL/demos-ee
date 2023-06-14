package br.com.thedevconf.communitylounge;

import jakarta.batch.operations.BatchRuntimeException;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("developers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DevelopersResource {

    @Inject
    TDC tdc;

    @GET
    public List<Developer> listAll() {
        return tdc.findAll().toList();
    }

    public record DeveloperInput(String name, List<String> technologies) {
    }

    @POST
    public Developer add(DeveloperInput input) {
        Developer developer = Developer.newDeveloper(input.name(),
                input.technologies());
        tdc.save(developer);
        return developer;
    }

    public record DeveloperUpdate(String name, List<String> technologies) {
    }

    @POST
    @Path("{id}")
    public Developer update(@PathParam("id") String id, DeveloperUpdate input) {
        System.out.println(id);
        Developer developer = tdc.findById(id)
                .orElseThrow(() -> new NotFoundException("id não encontrado!"));
        developer.setName(input.name());
        developer.setTechnologies(input.technologies());
        tdc.save(developer);
        return developer;
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") String id){
        tdc.deleteById(id);
    }

}
