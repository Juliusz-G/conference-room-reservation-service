package com.sda.conferenceroomreservationservice.exception.type.reservation;

public class ReservationCreationException extends RuntimeException {

    public ReservationCreationException() {
        super("Reservation can not be created!");
    }
}
