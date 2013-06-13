package com.talios.geekzone;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class Database {

    private Cache<String, Forum> forums = CacheBuilder.newBuilder().build();

    public Cache<String, Forum> getForums() {
        return forums;
    }


    public Database() {

        forums.put("gadgets", new Forum("gadgets", "Gadget Talk", "Gadgets and Toys", "example@example.com"));
        forums.put("offtopic", new Forum("offtopic", "Off-Topic", "Anything goes baby!", "example@example.com"));

    }
}
