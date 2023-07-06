package org.jnosql.demoee.payara.mongodb;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 */
@ApplicationPath("/")
@ApplicationScoped
public class RestApplication extends Application {
}
