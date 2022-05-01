package com.sda.conferenceroomreservationservice.conferenceroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conference-room")
public class ConferenceRoomController {

    private final ConferenceRoomService service;

    @Autowired
    public ConferenceRoomController(ConferenceRoomService service) {
        this.service = service;
    }
}
