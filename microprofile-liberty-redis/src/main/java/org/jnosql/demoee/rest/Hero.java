package org.jnosql.demoee.rest;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Objects;
import java.util.Set;

@Entity
@Schema
public record Hero(
        @Id
        @Schema(required = true)
        Integer id,
        @Column
        @Schema
        String name,
        @Column
        @Schema
        String secretIdentity,
        @Column
        @Schema
        String impactPhrase
) {

    public static Hero newHero(String name, String secretIdentity, String impactPhrase) {
        return new Hero(Objects.hash(name, secretIdentity, impactPhrase), name, secretIdentity, impactPhrase);
    }

    public boolean nameMatchesWith(String name) {
        return fuzzySearch(name, this.name());
    }

    public boolean secretIdentityMatchesWith(String pattern) {
        return fuzzySearch(pattern, this.secretIdentity());
    }

    public boolean impactPhraseMatchesWith(String pattern) {
        return fuzzySearch(pattern, this.impactPhrase());
    }

    private boolean isNotSearchable(String value) {
        return value == null || value.isBlank();
    }

    public Set<String> splitPhrase(String phrase) {
        return Set.of(phrase.split("[.,\\s]"));
    }

    public boolean fuzzySearch(String source, String target) {
        if (isNotSearchable(source) || isNotSearchable(target)) {
            return false;
        }
        Set<String> words = splitPhrase(source.toLowerCase());
        return words.stream().anyMatch(target.toLowerCase()::contains);
    }

}
