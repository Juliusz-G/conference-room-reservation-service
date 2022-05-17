package com.sda.conferenceroomreservationservice.controller;

import com.sda.conferenceroomreservationservice.model.dto.ReservationDto;
import com.sda.conferenceroomreservationservice.model.entity.Reservation;
import com.sda.conferenceroomreservationservice.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/create")
    public ResponseEntity<ReservationDto> create(@RequestBody ReservationDto reservationDto, Principal principal) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservationService.create(reservationDto, principal.getName()));
    }

    @DeleteMapping("/remove/{reservationId}")
    public ResponseEntity<Void> removeById(@PathVariable("reservationId") final Long reservationId, Principal principal) {
        reservationService.removeById(reservationId, principal.getName());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update/{reservationId}")
    public ResponseEntity<ReservationDto> update(@PathVariable final Long reservationId,
                                                 @RequestBody final Reservation reservation, Principal principal) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservationService.update(reservationId, reservation, principal.getName()));
    }

    @GetMapping("/get/{reservationId}")
    public ResponseEntity<ReservationDto> getById(@PathVariable Long reservationId, Principal principal) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(reservationService.getById(reservationId, principal.getName()));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReservationDto>> getAll(Principal principal) {
        return ResponseEntity.ok(reservationService.getAll(principal.getName()));
    }
}
