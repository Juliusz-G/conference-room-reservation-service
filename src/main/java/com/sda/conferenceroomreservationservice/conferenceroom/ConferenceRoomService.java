package com.sda.conferenceroomreservationservice.conferenceroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConferenceRoomService {

    private final ConferenceRoomRepository repository;

    @Autowired
    public ConferenceRoomService(ConferenceRoomRepository repository) {
        this.repository = repository;
    }
}
