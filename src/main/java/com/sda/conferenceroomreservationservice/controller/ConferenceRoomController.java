package com.sda.conferenceroomreservationservice.controller;

import com.sda.conferenceroomreservationservice.model.dto.ConferenceRoomDto;
import com.sda.conferenceroomreservationservice.model.entity.ConferenceRoom;
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

    // Create

    @PostMapping
    public ResponseEntity<ConferenceRoomDto> create(@RequestBody @Valid final ConferenceRoom conferenceRoom) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(conferenceRoomService.create(conferenceRoom));
    }

    // Read

    @GetMapping("/{conferenceRoomId}")
    public ResponseEntity<ConferenceRoomDto> getById(@PathVariable Long conferenceRoomId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(conferenceRoomService.getById(conferenceRoomId));
    }

    @GetMapping
    public ResponseEntity<List<ConferenceRoomDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(conferenceRoomService.getAll());
    }

    @GetMapping("/all-for-organisation/{organisationId}")
    public ResponseEntity<List<ConferenceRoomDto>> getAllByOrganisationId(@PathVariable Long organisationId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(conferenceRoomService.getAllByOrganisationId(organisationId));
    }

    // Update

//    @PutMapping
//    public ResponseEntity<ConferenceRoomDto> update(@RequestBody @Valid final ConferenceRoom conferenceRoom) {
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(conferenceRoomService.update(conferenceRoom));
//    }

    @PutMapping("/{conferenceRoomId}")
    public ResponseEntity<ConferenceRoomDto> updateById(
            @PathVariable final Long conferenceRoomId,
            @RequestBody @Valid final ConferenceRoom conferenceRoom
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(conferenceRoomService.updateById(conferenceRoomId, conferenceRoom));
    }

    // Delete

//    @DeleteMapping
//    public ResponseEntity<ConferenceRoomDto> remove(@RequestBody @Valid final ConferenceRoom conferenceRoom) {
//        conferenceRoomService.remove(conferenceRoom);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT)
//                .build();
//    }

    @DeleteMapping("/{conferenceRoomId}")
    public ResponseEntity<ConferenceRoomDto> removeById(@PathVariable final Long conferenceRoomId) {
        conferenceRoomService.removeById(conferenceRoomId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
}
