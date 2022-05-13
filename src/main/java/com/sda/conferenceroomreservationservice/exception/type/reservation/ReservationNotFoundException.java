package com.sda.conferenceroomreservationservice.exception.type.reservation;

import java.util.NoSuchElementException;

public class ReservationNotFoundException extends NoSuchElementException {

    public ReservationNotFoundException() {
        super("Reservation not found!");
    }
}
