package com.sda.conferenceroomreservationservice.controller;

import com.sda.conferenceroomreservationservice.model.dto.ReservationDto;
import com.sda.conferenceroomreservationservice.model.entity.Reservation;
import com.sda.conferenceroomreservationservice.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    // Create

    @PostMapping
    public ResponseEntity<ReservationDto> create(@RequestBody @Valid final Reservation reservation) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservationService.create(reservation));
    }

    // Read

    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationDto> getById(@PathVariable final Long reservationId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(reservationService.getById(reservationId));
    }

    @GetMapping
    public ResponseEntity<List<ReservationDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(reservationService.getAll());
    }

    // Update

//    @PutMapping
//    public ResponseEntity<ReservationDto> update(@RequestBody @Valid final Reservation reservation) {
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(reservationService.update(reservation));
//    }

    @PutMapping("/{reservationId}")
    public ResponseEntity<ReservationDto> updateById(
            @RequestBody @Valid final Reservation reservation,
            @PathVariable final Long reservationId
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservationService.updateById(reservationId, reservation));
    }

    // Delete

//    @DeleteMapping
//    public ResponseEntity<ReservationDto> remove(@RequestBody @Valid final Reservation reservation) {
//        reservationService.remove(reservation);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT)
//                .build();
//    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<ReservationDto> removeById(@PathVariable("reservationId") final Long reservationId) {
        reservationService.removeById(reservationId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
}
