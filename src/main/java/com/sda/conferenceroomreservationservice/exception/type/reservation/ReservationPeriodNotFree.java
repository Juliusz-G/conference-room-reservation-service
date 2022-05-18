package com.sda.conferenceroomreservationservice.exception.type.reservation;

public class ReservationPeriodNotFree extends RuntimeException {

    public ReservationPeriodNotFree() {
        super("Reservation period is not free!");
    }
}
