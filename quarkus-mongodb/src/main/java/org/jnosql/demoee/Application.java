package org.jnosql.demoee;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.eclipse.jnosql.mapping.reflection.EntitiesMetadata;

@ApplicationScoped
public class Application {

    @Inject
    private EntitiesMetadata entities;

    void onStart(@Observes StartupEvent ev) {
        entities.get(Fish.class);
    }
}
