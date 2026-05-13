package com.roombooking.core;

import java.time.LocalDate;

public class main {

    public static void main(String[] args) throws IllegalArgumentException {
        BookingManager bm = new BookingManager();
        User user1 = bm.addUser("Tushar");
        User user2 = bm.addUser("Pranay");
        User user3 = bm.addUser("Shukla");
        User user4 = bm.addUser("Rohit");
        User user5 = bm.addUser("Shaurya");

        Room room1 = bm.addRoom();
        Room room2 = bm.addRoom();
        Room room3 = bm.addRoom();
        Room room4 = bm.addRoom();

        Booking booking1 = bm.bookRoom(user1.getUserId(), LocalDate.of(2026, 05, 14), LocalDate.of(2026, 05, 18));
        Booking booking2 = bm.bookRoom(user2.getUserId(), LocalDate.of(2026, 05, 14), LocalDate.of(2026, 05, 18));
        Booking booking3 = bm.bookRoom(user3.getUserId(), LocalDate.of(2026, 05, 14), LocalDate.of(2026, 05, 18));
        Booking booking4 = bm.bookRoom(user4.getUserId(), LocalDate.of(2026, 05, 14), LocalDate.of(2026, 05, 18));
//        Booking booking5 = bm.bookRoom(user5.getUserId(), LocalDate.of(2026, 05, 14), LocalDate.of(2026, 05, 18));
        try {
            Booking booking5 = bm.bookRoom(user5.getUserId(), LocalDate.of(2026, 05, 14), LocalDate.of(2026, 05, 18));
        } catch (IllegalStateException e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        System.out.println("Booking1 room: " + booking1.getRoomId());
        System.out.println("Bookings by user1: " + bm.getBookingsByUser(user1.getUserId()));
        System.out.println("Bookings for room1: " + bm.getBookingsByRoom(room1.getRoomId()));

        bm.cancelBooking(booking4.getBookingId(), user4.getUserId());
        Booking booking5 = bm.bookRoom(user5.getUserId(), LocalDate.of(2026, 05, 14), LocalDate.of(2026, 05, 18));
    }
}
