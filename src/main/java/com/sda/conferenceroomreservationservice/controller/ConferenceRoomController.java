package com.sda.conferenceroomreservationservice.controller;

import com.sda.conferenceroomreservationservice.model.dto.ConferenceRoomDto;
import com.sda.conferenceroomreservationservice.service.ConferenceRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conference-room")
public class ConferenceRoomController {

    private final ConferenceRoomService conferenceRoomService;

    @Autowired
    public ConferenceRoomController(ConferenceRoomService conferenceRoomService) {
        this.conferenceRoomService = conferenceRoomService;
    }

    public ResponseEntity<ConferenceRoomDto> create() {
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<ConferenceRoomDto> remove() {
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<ConferenceRoomDto> removeById() {
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<ConferenceRoomDto> update() {
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Optional<ConferenceRoomDto>> getById() {
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Optional<ConferenceRoomDto>> getByName() {
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<List<ConferenceRoomDto>> getAll() {
        return ResponseEntity.noContent().build();
    }
}
