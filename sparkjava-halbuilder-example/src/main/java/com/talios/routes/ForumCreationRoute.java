package com.talios.routes;

import com.google.inject.Inject;
import com.talios.App;
import com.talios.geekzone.Database;
import com.talios.geekzone.Forum;
import spark.Request;
import spark.Response;
import spark.Route;

public class ForumCreationRoute implements Route {

    public static final String PATH = "/forums";

    private Database database;

    @Inject
    public ForumCreationRoute(Database database) {
        this.database = database;
    }

    @Override
    public Object handle(Request request, Response response) {

        String id = request.queryParams("id");
        String name = request.queryParams("name");
        String description = request.queryParams("description");
        String owner = request.queryParams("owner");

        database.getForums().put(id, new Forum(id, name, description, owner));

        response.redirect(App.BASE_HREF + "/forums/" + id);
        response.status(201);

        return "";

    }
}
