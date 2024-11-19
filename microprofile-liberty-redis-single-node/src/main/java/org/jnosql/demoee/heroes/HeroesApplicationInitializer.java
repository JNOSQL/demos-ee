package org.jnosql.demoee.heroes;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.eclipse.jnosql.mapping.keyvalue.KeyValueDatabase;

import java.util.Map;

import static java.util.Set.of;
import static org.jnosql.demoee.heroes.Hero.newHero;

@ApplicationScoped
public class HeroesApplicationInitializer {

    @Inject
    @KeyValueDatabase("heroes")
    Map<Integer, Hero> heroes;

    public void onInitialized(@Observes @Initialized(ApplicationScoped.class) Object event) {
        of(
                newHero("Superman", "Clark Kent", "Up, up and away!"),
                newHero("Batman", "Bruce Wayne", "I am vengeance, I am the night!"),
                newHero("Wonder", "Diana Prince", "I will fight for those who cannot fight for themselves!"),
                newHero("Flash", "Barry Allen", "Life doesn't give us purpose. We give life purpose!"),
                newHero("Green Lantern", "Hal Jordan", "In brightest day, in blackest night, no evil shall escape my sight!"),
                newHero("Aquaman", "Arthur Curry", "I don't belong to the land or the sea!"),
                newHero("Cyborg", "Victor Stone", "I'm not broken, and I'm not alone!"),
                newHero("Shazam", "Billy Batson", "Shazam!")
        )
                .stream()
                .filter(hero -> !heroes.containsKey(hero.id()))
                .forEach(hero -> heroes.put(hero.id(), hero));
    }
}