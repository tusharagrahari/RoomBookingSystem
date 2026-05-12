package com.roombooking.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingManager implements RoomBookingSystem {
    private Map<String, Booking> bookings;
    private Map<String, User> users;
    private Map<String, Room> rooms;
    private Map<String, List<Booking>> bookingsByUser;
    private Map<String, List<Booking>> bookingsByRoom;
    private Map<String, Map<LocalDate, String>> roomAvailability;

    public BookingManager() {
        this.bookings = new HashMap<>();
        this.users = new HashMap<>();
        this.rooms = new HashMap<>();
        this.bookingsByUser = new HashMap<>();
        this.bookingsByRoom = new HashMap<>();
        this.roomAvailability = new HashMap<>();
    }

    public User addUser(String name) {
        User user = new User(name);
        users.put(user.getUserId(), user);
        bookingsByUser.put(user.getUserId(), new ArrayList<>());
        return user;
    }

    public Room addRoom() {
        Room room = new Room();
        rooms.put(room.getRoomId(), room);
        bookingsByRoom.put(room.getRoomId(), new ArrayList<>());
        roomAvailability.put(room.getRoomId(), new HashMap<>());
        return room;
    }

    public Booking bookRoom(String userId, LocalDate starDate, LocalDate endDate) {


    }

    public void cancelBooking(String bookingId, String userId) {

    }

    public List<Booking> getBookingsByUser(String userId) {

    }

    public List<Booking> getBookingsByRoom(String roomId) {

    }
}
