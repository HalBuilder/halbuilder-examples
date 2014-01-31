package com.theoryinpractise.halbuilder.jersey;

import com.theoryinpractise.halbuilder.api.Representation;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.net.URISyntaxException;

import static com.theoryinpractise.halbuilder.jersey.Main.mkUri;
import static com.theoryinpractise.halbuilder.jersey.Main.representationFactory;

@Path("myresource")
public class MyResource {

    /**
     * A simple resource that returns a HAL Representation
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces({RepresentationFactory.HAL_JSON, RepresentationFactory.HAL_XML})
    public Representation getIt() throws URISyntaxException {

        Representation rep = representationFactory.newRepresentation(mkUri(MyResource.class));
        rep.withProperty("message", "Got it!");

        return rep;
    }


}
