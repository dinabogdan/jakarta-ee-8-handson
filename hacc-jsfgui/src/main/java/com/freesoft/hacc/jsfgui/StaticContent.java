package com.freesoft.hacc.jsfgui;

import java.io.InputStream;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("")
@Stateless
public class StaticContent {

    @Inject
    ServletContext servletContext;

    @GET
    @Path("{path: ^static\\/.*}")
    public Response staticResources(@PathParam("path") final String path) {
        final InputStream resource = servletContext.getResourceAsStream(String.format("/static/%s", path));

        return null == resource ? Response.status(Response.Status.NOT_FOUND).build() : Response.ok().entity(resource).build();
    }
}
