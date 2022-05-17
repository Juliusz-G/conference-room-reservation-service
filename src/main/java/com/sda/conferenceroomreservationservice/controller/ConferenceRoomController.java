package com.sda.conferenceroomreservationservice.controller;

import com.sda.conferenceroomreservationservice.model.dto.ConferenceRoomDto;
import com.sda.conferenceroomreservationservice.model.entity.ConferenceRoom;
import com.sda.conferenceroomreservationservice.service.ConferenceRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/conference-room")
@RequiredArgsConstructor
public class ConferenceRoomController {

    private final ConferenceRoomService conferenceRoomService;

    @PostMapping("/create")
    public ResponseEntity<ConferenceRoomDto> create(@RequestBody @Valid final ConferenceRoom conferenceRoom, Principal principal) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(conferenceRoomService.create(conferenceRoom, principal.getName()));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ConferenceRoomDto> remove(@RequestBody @Valid final ConferenceRoom conferenceRoom, Principal principal) {
        conferenceRoomService.remove(conferenceRoom, principal.getName());
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("/update")
    public ResponseEntity<ConferenceRoomDto> update(@RequestBody @Valid final ConferenceRoom conferenceRoom, Principal principal) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(conferenceRoomService.update(conferenceRoom, principal.getName()));
    }

    @GetMapping("/get/{conferenceRoomId}")
    public ResponseEntity<ConferenceRoomDto> getById(@PathVariable Long conferenceRoomId, Principal principal) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(conferenceRoomService.getById(conferenceRoomId, principal.getName()));
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<ConferenceRoomDto>> getAll(Principal principal) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(conferenceRoomService.getAll(principal.getName()));
    }

    @GetMapping("/get/all-for-organisation/{organisationId}")
    public ResponseEntity<List<ConferenceRoomDto>> getAllByOrganisationId(@PathVariable Long organisationId, Principal principal) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(conferenceRoomService.getAllByOrganisationId(organisationId, principal.getName()));
    }
}
