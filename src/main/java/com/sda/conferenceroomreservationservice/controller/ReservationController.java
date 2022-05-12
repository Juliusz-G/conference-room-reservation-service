package com.sda.conferenceroomreservationservice.controller;

import com.sda.conferenceroomreservationservice.model.entity.Reservation;
import com.sda.conferenceroomreservationservice.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    public ReservationController(ReservationService service) {
        super(service);
    }

    public ResponseEntity<Reservation> create(Reservation entity) {
        return super.create(entity);
    }

    public ResponseEntity<Reservation> remove(Reservation entity) {
        super.remove(entity);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Reservation> removeById(Long id) {
        super.removeById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Reservation> update(Reservation entity) {
        return super.update(entity);
    }

    public ResponseEntity<Optional<Reservation>> getById(Long id) {
        return super.getById(id);
    }


    public ResponseEntity<List<Reservation>> getAll() {
        return super.getAll();
    }
}
