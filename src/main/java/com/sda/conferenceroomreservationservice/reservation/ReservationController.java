package com.sda.conferenceroomreservationservice.reservation;

import com.sda.conferenceroomreservationservice.generics.genericcontroller.GenericControllerImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservation")
public class ReservationController extends GenericControllerImpl<Reservation> {

    public ReservationController(ReservationService service) {
        super(service);
    }

    @Override
    public ResponseEntity<Reservation> create(Reservation entity) {
        return super.create(entity);
    }

    @Override
    public void remove(Reservation entity) {
        super.remove(entity);
    }

    @Override
    public void removeById(Long id) {
        super.removeById(id);
    }

    @Override
    public ResponseEntity<Reservation> update(Reservation entity) {
        return super.update(entity);
    }

    @Override
    public ResponseEntity<Optional<Reservation>> getById(Long id) {
        return super.getById(id);
    }

    @Override
    public ResponseEntity<Optional<Reservation>> getByName(String name) {
        return super.getByName(name);
    }

    @Override
    public ResponseEntity<List<Reservation>> getAll() {
        return super.getAll();
    }
}
