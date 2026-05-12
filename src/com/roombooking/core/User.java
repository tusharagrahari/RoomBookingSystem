package com.roombooking.core;

import java.util.UUID;

public class User {
    private String userId;
    private String name;

    public User(String name) {
        this.userId = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getName() {
        return this.name;
    }
}
