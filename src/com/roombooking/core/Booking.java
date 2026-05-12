package com.roombooking.core;

import java.time.LocalDate;
import java.util.UUID;

public class Booking {
    private String bookingId;
    private String userId;
    private String roomId;
    private LocalDate startDate;
    private LocalDate endDate;

    public Booking(String userId, String roomId, LocalDate startDate, LocalDate endDate) {
        this.bookingId = UUID.randomUUID().toString();
        this.userId = userId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getBookingId() {
        return this.bookingId;
    }

    public String getRoomId() {
        return this.roomId;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }
}
