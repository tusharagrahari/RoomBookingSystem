package com.roombooking.core;

import java.time.LocalDate;
import java.util.List;

public interface RoomBookingSystem {
    User addUser(String name);
    Room addRoom();
    Booking bookRoom(String userId, LocalDate startDate, LocalDate endDate);
    void cancelBooking(String bookingId, String userId);
    List<Booking> getBookingsByUser(String userId);
    List<Booking> getBookingsByRoom(String roomId);
}