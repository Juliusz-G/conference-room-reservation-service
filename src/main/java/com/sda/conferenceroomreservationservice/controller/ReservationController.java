package com.sda.conferenceroomreservationservice.controller;

import com.sda.conferenceroomreservationservice.model.dto.ReservationDto;
import com.sda.conferenceroomreservationservice.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    public ResponseEntity<ReservationDto> create() {
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<ReservationDto> remove() {
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<ReservationDto> removeById() {
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<ReservationDto> update() {
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Optional<ReservationDto>> getById() {
        return ResponseEntity.noContent().build();
    }


    public ResponseEntity<List<ReservationDto>> getAll() {
        return ResponseEntity.noContent().build();
    }
}
