package com.theoryinpractise.halbuilder.jersey;

import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import com.theoryinpractise.halbuilder.standard.StandardRepresentationFactory;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/myapp/";

    // Setup a reusable RepresentationFactory with standard links/settings
    public static final RepresentationFactory representationFactory = new StandardRepresentationFactory()
            .withFlag(RepresentationFactory.PRETTY_PRINT)
            .withLink("website", "http://gotohal.net");


    public static URI mkUri(final Class<MyResource> resourceClass) throws URISyntaxException {
        URI href = UriBuilder.fromResource(resourceClass).build();
        return new URI(Main.BASE_URI).resolve(href);
    }


    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans fors JAX-RS classes in this project, and also the HalBuilder JAX-RS
        // support package - com.theoryinpractise.halbuilder.jaxrs
        final ResourceConfig rc = new ResourceConfig().packages(
                "com.theoryinpractise.halbuilder.jaxrs",
                "com.theoryinpractise.halbuilder.jersey"
                );

        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }
}

