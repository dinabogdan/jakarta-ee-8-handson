package com.freesoft.hacc.jsfgui;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/")
public class RestDate {

    @GET
    @Path("date")
    @Produces("application/json")
    public Response date(@QueryParam("dateFormat") @DefaultValue("") String dateFormat) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();

        String outString = "";
        String errorMessage = "";

        outString = ("".equals(dateFormat) ? zonedDateTime.toString() : zonedDateTime.format(DateTimeFormatter.ofPattern(dateFormat)));

        return Response.ok().entity(
                "{" + "\"date\":\"" + outString + "}"
        ).build();
    }
}
