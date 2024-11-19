package org.jnosql.demoee.heroes;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.jnosql.mapping.keyvalue.KeyValueDatabase;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

@ApplicationScoped
public class Heroes {

    @Inject
    @KeyValueDatabase("heroes")
    Map<Integer, Hero> heroes;

    public Iterable<Hero> findHeroesBy(HeroesQuery heroesQuery) {

        var nameMatches = Optional
                .ofNullable(heroesQuery.name())
                .filter(Predicate.not(String::isBlank))
                .map(m -> (Predicate<Hero>) h -> h.nameMatchesWith(m))
                .orElse(null);

        var secretIdentityMatches = Optional
                .ofNullable(heroesQuery.secretIdentity())
                .filter(Predicate.not(String::isBlank))
                .map(m -> (Predicate<Hero>) h -> h.secretIdentityMatchesWith(m))
                .orElse(null);

        var impactPhraseMatches = Optional
                .ofNullable(heroesQuery.impactPhrase())
                .filter(Predicate.not(String::isBlank))
                .map(m -> (Predicate<Hero>) h -> h.impactPhraseMatchesWith(m))
                .orElse(null);

        var matcherFilters = Arrays.stream(new Predicate[]{
                        nameMatches,
                        secretIdentityMatches,
                        impactPhraseMatches})
                .filter(Objects::nonNull)
                .reduce((a, b) -> a.or(b));


        if (matcherFilters.isPresent()) {

            return heroes.values()
                    .stream()
                    .filter(matcherFilters.orElse(h -> false)).toList();
        }

        return heroes.values().stream().toList();
    }

    public Optional<Hero> getById(Integer id) {
        return Optional.ofNullable(heroes.get(id));
    }

    public Hero add(Hero hero) {
        Objects.requireNonNull(hero, "hero is required");
        heroes.put(hero.id(), hero);
        return hero;
    }

    public Optional<Hero> remove(Integer id) {
       return Optional.ofNullable(id)
                .flatMap(existentId -> Optional.ofNullable(heroes.remove(existentId)));
    }


}
