package org.eclipse.jnosql.demoee.jnosql.bean.validation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.nosql.Template;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;


@ApplicationScoped
@OpenAPIDefinition(info = @Info(title = "Students endpoint", version = "1.0"))

@Path("students")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class StudentsResource {


    @Inject
    Template template;


    @APIResponses(value = {
            @APIResponse(
                    responseCode = "201",
                    description = "Student",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    ref = "NewStudent"))
            )})
    @POST
    public Student add(@Valid NewStudent newStudent){

        var student = newStudent.toModel();

        return template.insert(student);
    }


}
