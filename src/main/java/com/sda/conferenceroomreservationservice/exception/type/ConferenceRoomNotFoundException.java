package com.sda.conferenceroomreservationservice.exception.type;

public class ConferenceRoomNotFoundException extends RuntimeException {

    public ConferenceRoomNotFoundException() {
        super("Conference room not found!");
    }
}
