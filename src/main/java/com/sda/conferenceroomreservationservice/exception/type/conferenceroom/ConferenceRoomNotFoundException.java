package com.sda.conferenceroomreservationservice.exception.type.conferenceroom;

import java.util.NoSuchElementException;

public class ConferenceRoomNotFoundException extends NoSuchElementException {

    public ConferenceRoomNotFoundException() {
        super("Conference room not found!");
    }
}
