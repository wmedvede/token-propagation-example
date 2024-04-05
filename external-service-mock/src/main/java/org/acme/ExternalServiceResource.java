package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

@Path("/")
public class ExternalServiceResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalServiceResource.class);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    @Path("trackers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tracker> getTrackers(@Context HttpHeaders httpHeaders) {
        LOGGER.debug("getTrackers.httpHeaders: {}", httpHeaders.getRequestHeaders());
        return Arrays.asList(new Tracker("1", "Tracker1"), new Tracker("2", "Tracker2"));
    }
}
