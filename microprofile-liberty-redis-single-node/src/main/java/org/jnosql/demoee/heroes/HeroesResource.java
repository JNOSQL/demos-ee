package org.jnosql.demoee.heroes;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import static org.jnosql.demoee.heroes.HeroesQuery.queryBuilder;

@Path("/heroes")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class HeroesResource {

    @Inject
    Heroes heroes;

    @GET
    public Iterable<Hero> heroes(
            @QueryParam("name") String name,
            @QueryParam("secretIdentity") String secretIdentity,
            @QueryParam("impactPhrase") String impactPhrase
    ) {

        return heroes.findHeroesBy(queryBuilder()
                .byName(name)
                .bySecretIdentity(secretIdentity)
                .byImpactPhrase(impactPhrase).build());

    }

    @GET
    @Path("/{id}")
    public Hero getHeroById(
            @PathParam("id")
            @Schema(required = true) final Integer id) {
        return heroes.getById(id)
                .orElseThrow(() -> new NotFoundException("Hero not found"));
    }

    @Schema
    public record NewHero(
            @NotBlank
            @Schema
            String name,
            @Schema
            String secretIdentity,
            @Schema
            String impactPhrase) {
        Hero toEntity() {
            return Hero.newHero(name(), secretIdentity(), impactPhrase());
        }
    }

    @POST
    public Hero add(@Valid NewHero request) {
        var newHero = request.toEntity();
        return heroes.add(newHero);
    }

    @DELETE
    @Path("/{id}")
    public Hero delete(@PathParam("id")
                       @Schema(required = true) final Integer id) {
        return this.heroes.remove(id).orElseThrow(() -> new NotFoundException("Hero not found"));
    }


}
