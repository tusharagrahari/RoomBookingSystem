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

    public synchronized Booking bookRoom(String userId, LocalDate startDate, LocalDate endDate) {
        if (!startDate.isBefore(endDate)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }

        if (!users.containsKey(userId)) {
            throw new IllegalArgumentException("User not found");
        }

        for (Room room : rooms.values()) {
            boolean isAvailable = true;
            Map<LocalDate, String> dayMap = roomAvailability.get(room.getRoomId());

            for (LocalDate d = startDate; d.isBefore(endDate); d = d.plusDays(1)) {
                if (dayMap.containsKey(d)) {
                    isAvailable = false;
                    break;
                }
            }

            if (isAvailable) {
                Booking booking = new Booking(userId, room.getRoomId(), startDate, endDate);
                bookings.put(booking.getBookingId(), booking);
                bookingsByUser.get(userId).add(booking);
                bookingsByRoom.get(room.getRoomId()).add(booking);

                for (LocalDate d = startDate; d.isBefore(endDate); d = d.plusDays(1)) {
                    dayMap.put(d, booking.getBookingId());
                }

                return booking;
            }
        }

        throw new IllegalStateException("No rooms available for the given period");
    }

    public synchronized void cancelBooking(String bookingId, String userId) {
        Booking booking = bookings.get(bookingId);
        if (booking == null) {
            throw new IllegalArgumentException("Booking not found");
        }

        if (!booking.getUserId().equals(userId)) {
            throw new IllegalArgumentException("Not authorized to cancel this booking");
        }

        bookings.remove(bookingId);
        bookingsByUser.get(userId).removeIf(b -> b.getBookingId().equals(bookingId));
        bookingsByRoom.get(booking.getRoomId()).removeIf(b -> b.getBookingId().equals(bookingId));

        Map<LocalDate, String> dayMap = roomAvailability.get(booking.getRoomId());
        for (LocalDate d = booking.getStartDate(); d.isBefore(booking.getEndDate()); d = d.plusDays(1)) {
            dayMap.remove(d);
        }
    }

    public List<Booking> getBookingsByUser(String userId) {
        if (!users.containsKey(userId)) {
            throw new IllegalArgumentException("User not found");
        }
        return bookingsByUser.get(userId);
    }

    public List<Booking> getBookingsByRoom(String roomId) {
        if (!rooms.containsKey(roomId)) {
            throw new IllegalArgumentException("Room not found");
        }
        return bookingsByRoom.get(roomId);
    }
}
