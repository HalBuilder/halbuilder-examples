package com.talios.routes;

import com.google.inject.Inject;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import spark.Request;
import spark.Response;
import spark.Route;

public class RootRoute extends Route {

    public static final String PATH = "/";

    @Inject
    private RepresentationFactory representationFactory;

    @Inject
    public RootRoute() {
        super(PATH);
    }

    @Override
    public Object handle(Request request, Response response) {
        return representationFactory.newRepresentation("/")
                              .withLink("/forums", "forums")
                              .toString(request.headers("Accept"));
    }
}
