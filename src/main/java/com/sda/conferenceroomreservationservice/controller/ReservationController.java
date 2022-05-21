package com.sda.conferenceroomreservationservice.controller;

import com.sda.conferenceroomreservationservice.model.request.ReservationRequest;
import com.sda.conferenceroomreservationservice.model.response.ReservationResponse;
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

    @PostMapping
    public ResponseEntity<ReservationResponse> create(@RequestBody @Valid final ReservationRequest reservationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservationService.create(reservationRequest));
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationResponse> getById(@PathVariable final Long reservationId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(reservationService.getById(reservationId));
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(reservationService.getAll());
    }

    @PutMapping("/{reservationId}")
    public ResponseEntity<ReservationResponse> updateById(
            @RequestBody @Valid final ReservationRequest reservationRequest,
            @PathVariable final Long reservationId
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservationService.updateById(reservationId, reservationRequest));
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> removeById(@PathVariable("reservationId") final Long reservationId) {
        reservationService.removeById(reservationId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
}
