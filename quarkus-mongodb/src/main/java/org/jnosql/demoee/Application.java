package org.jnosql.demoee;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.eclipse.jnosql.mapping.reflection.EntitiesMetadata;

import java.util.logging.Logger;

@ApplicationScoped
public class Application {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());
    @Inject
    private EntitiesMetadata entities;

    void onStart(@Observes StartupEvent ev) {
        entities.get(Fish.class);
    }
}
