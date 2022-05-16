package com.sda.conferenceroomreservationservice.exception.type.conferenceroom;

public class ConferenceRoomAlreadyExists extends RuntimeException {

    public ConferenceRoomAlreadyExists() {
        super("Conference room already exists!");
    }
}
