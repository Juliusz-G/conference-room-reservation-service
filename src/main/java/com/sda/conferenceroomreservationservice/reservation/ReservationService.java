package com.sda.conferenceroomreservationservice.reservation;

import com.sda.conferenceroomreservationservice.generics.genericservice.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService extends GenericServiceImpl<Reservation> {

    @Autowired
    public ReservationService(ReservationRepository repository) {
        super(repository);
    }

    @Override
    public Reservation create(Reservation entity) {
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
    public Reservation update(Reservation entity) {
        return super.update(entity);
    }

    @Override
    public Optional<Reservation> getById(Long id) {
        return super.getById(id);
    }

    @Override
    public Optional<Reservation> getByName(String name) {
        return super.getByName(name);
    }

    @Override
    public List<Reservation> getAll() {
        return super.getAll();
    }
}
