package org.jnosql.demoee.payara.mongodb;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 *
 */
@ApplicationPath("/")
@ApplicationScoped
public class RestApplication extends Application {
}
