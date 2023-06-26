package org.eclipse.jnosql.demoee.jnosql.bean.validation.openapi;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/booking")
@ApplicationScoped
@OpenAPIDefinition(info = @Info(title = "Booking endpoint", version = "1.0"))
public class BookingController {

    @APIResponses(value = {
            @APIResponse(
                    responseCode = "200",
                    description = "Booking for id",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    ref = "Booking"))
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "No booking found for the id.")
    })
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{bookingId}")
    public Response getBooking(@PathParam("bookingId") String bookingId) {
        return Response
                .status(Response.Status.OK)
                .entity(Booking.booking(bookingId, Destination.destination("New Rearendia", "Wheeli")))
                .build();
    }

}
