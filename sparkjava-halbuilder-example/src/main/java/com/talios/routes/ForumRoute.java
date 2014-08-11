package com.talios.routes;

import com.google.inject.Inject;
import com.talios.geekzone.Database;
import com.talios.geekzone.Forum;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.halt;

public class ForumRoute implements Route {

    public static final String PATH = "/forums/:id";

    private Database database;

    @Inject
    private RepresentationFactory representationFactory;

    @Inject
    public ForumRoute(Database database) {
        this.database = database;
    }

    @Override
    public Object handle(Request request, Response response) {

        final String id = request.params("id");

        if (database.getForums().asMap().containsKey(id)) {
            Forum forum = database.getForums().getIfPresent(id);

            return representationFactory.newRepresentation("/forums/" + forum.getId())
                                  .withLink("/forums", "forums")
                                  .withBean(forum)
                                  .toString(request.headers("Accept"));

        } else {
            halt(404, String.format("Panic! Unknown forum with code '%s'", id));
        }

        return null;
    }
}
