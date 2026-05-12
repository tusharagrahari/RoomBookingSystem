package com.roombooking.core;

import java.util.UUID;

public class Room {
    private String roomId;

    public Room() {
        this.roomId = UUID.randomUUID().toString();
    }

    public String getRoomId() {
        return this.roomId;
    }
}
