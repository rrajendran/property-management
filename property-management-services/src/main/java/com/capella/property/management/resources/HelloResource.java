package com.capella.property.management.resources;

import com.capella.property.management.entity.Person;
import com.capella.property.management.services.GreeterService;
import com.google.inject.Inject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("hello")
public class HelloResource {

    private final GreeterService greeterService;

    @Inject
    public HelloResource(final GreeterService greeterService) {
        this.greeterService = greeterService;
    }

    @GET
    @Path("{name}")
    @Produces("application/json")
    public Person hello(@PathParam("name") final String name) throws Exception {
        return greeterService.greet(name);
    }

}