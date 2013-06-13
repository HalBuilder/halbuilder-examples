package com.talios;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.talios.geekzone.Database;
import com.talios.routes.ForumCreationRoute;
import com.talios.routes.ForumListingRoute;
import com.talios.routes.ForumRoute;
import com.talios.routes.RootRoute;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import com.theoryinpractise.halbuilder.standard.StandardRepresentationFactory;

import static spark.Spark.get;
import static spark.Spark.post;

public class App {

    public static final String BASE_HREF = "http://localhost:4567";

    private static final Injector repository = Guice.createInjector(new Module() {
        public void configure(Binder binder) {
            RepresentationFactory rf = new StandardRepresentationFactory()
                    .withFlag(RepresentationFactory.PRETTY_PRINT);
            binder.bind(RepresentationFactory.class).toInstance(rf);
            binder.bind(Database.class).toInstance(new Database());
        }
    });

    public static void main(String[] args) {

        get(repository.getInstance(RootRoute.class));
        get(repository.getInstance(ForumListingRoute.class));
        post(repository.getInstance(ForumCreationRoute.class));
        get(repository.getInstance(ForumRoute.class));

    }

}
