package org.jnosql.sample;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
public record Developer(
        @Id String id,
        @Column String name,
        @Column LocalDate birthday
) {

    public static Developer newDeveloper(String name, LocalDate birthday) {
        Objects.requireNonNull(name, "name is required");
        Objects.requireNonNull(birthday, "birthday is required");
        return new Developer(
                UUID.randomUUID().toString(),
                name,
                birthday);
    }
}
