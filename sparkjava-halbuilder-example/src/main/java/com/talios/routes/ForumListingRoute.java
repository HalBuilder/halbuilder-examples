package com.talios.routes;

import com.google.common.base.Predicate;
import com.google.inject.Inject;
import com.talios.geekzone.AuthStuff;
import com.talios.geekzone.Database;
import com.talios.geekzone.Forum;
import com.theoryinpractise.halbuilder.api.Representation;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import spark.Request;
import spark.Response;
import spark.Route;

public class ForumListingRoute implements Route {

    public static final String PATH = "/forums";

    private Database database;

    private RepresentationFactory representationFactory;

    private final Predicate<Request> canCreateForum;

    @Inject
    public ForumListingRoute(AuthStuff authStuff, Database database, RepresentationFactory representationFactory) {
        this.database = database;
        this.representationFactory = representationFactory;
        canCreateForum = authStuff.makeAuthPredicate("CUSTOMER CREATE");
    }

    @Override
    public Object handle(Request request, Response response) {
        Representation resource = representationFactory.newRepresentation(PATH);

        if (canCreateForum.apply(request)) {
           resource.withLink("create", ForumCreationRoute.PATH);
        }

        for (Forum forum : database.getForums().asMap().values()) {
            Representation forumResource = representationFactory.newRepresentation("/forums/" + forum.getId())
                                                    .withProperty("id", forum.getId())
                                                    .withProperty("description", forum.getDescription());

            resource.withRepresentation("forum", forumResource);
        }

        response.status(203);  // not authoritative

        return resource.toString(request.headers("Accept"));
    }
}
