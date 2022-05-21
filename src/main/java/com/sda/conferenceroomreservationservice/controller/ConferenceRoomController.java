package com.sda.conferenceroomreservationservice.controller;

import com.sda.conferenceroomreservationservice.model.request.ConferenceRoomRequest;
import com.sda.conferenceroomreservationservice.model.response.ConferenceRoomResponse;
import com.sda.conferenceroomreservationservice.service.ConferenceRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/conference-room")
@RequiredArgsConstructor
public class ConferenceRoomController {

    private final ConferenceRoomService conferenceRoomService;

    @PostMapping
    public ResponseEntity<ConferenceRoomResponse> create(@RequestBody @Valid final ConferenceRoomRequest conferenceRoomRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(conferenceRoomService.create(conferenceRoomRequest));
    }

    @GetMapping("/{conferenceRoomId}")
    public ResponseEntity<ConferenceRoomResponse> getById(@PathVariable final Long conferenceRoomId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(conferenceRoomService.getById(conferenceRoomId));
    }

    @GetMapping
    public ResponseEntity<List<ConferenceRoomResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(conferenceRoomService.getAll());
    }

    @GetMapping("/all-for-organisation/{organisationId}")
    public ResponseEntity<List<ConferenceRoomResponse>> getAllByOrganisationId(@PathVariable final Long organisationId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(conferenceRoomService.getAllByOrganisationId(organisationId));
    }

    @PutMapping("/{conferenceRoomId}")
    public ResponseEntity<ConferenceRoomResponse> updateById(
            @PathVariable final Long conferenceRoomId,
            @RequestBody @Valid final ConferenceRoomRequest conferenceRoomRequest
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(conferenceRoomService.updateById(conferenceRoomId, conferenceRoomRequest));
    }

    @DeleteMapping("/{conferenceRoomId}")
    public ResponseEntity<Void> removeById(@PathVariable final Long conferenceRoomId) {
        conferenceRoomService.removeById(conferenceRoomId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
}
