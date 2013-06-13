package com.talios.geekzone;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import spark.Request;

import java.util.Set;

public class AuthStuff {

    public Predicate<Request> makeAuthPredicate(final String roleTypes) {
        return new AutenticationPredicate(roleTypes);
    }

    private static class AutenticationPredicate implements Predicate<Request> {
        private Set<String> requiredRoles;

        public AutenticationPredicate(String roleTypes) {
            this.requiredRoles = Sets.newHashSet(Splitter.on(" ").split(roleTypes));
        }

        public boolean apply(Request request) {
            Set<String> userAuthSet = Sets.newHashSet(Splitter.on(" ").split(Objects.firstNonNull(request.headers("auth"), "")));
            return userAuthSet.containsAll(requiredRoles);
        }
    }
}
