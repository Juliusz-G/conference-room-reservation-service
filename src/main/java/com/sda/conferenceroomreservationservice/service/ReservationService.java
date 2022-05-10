package com.sda.conferenceroomreservationservice.service;

import com.sda.conferenceroomreservationservice.model.entity.Reservation;
import com.sda.conferenceroomreservationservice.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService extends GenericServiceImpl<Reservation> {

    @Autowired
    public ReservationService(final ReservationRepository repository) {
        super(repository);
    }

    @Override
    public Reservation create(final Reservation entity) {
        return super.create(entity);
    }

    @Override
    public void remove(final Reservation entity) {
        super.remove(entity);
    }

    @Override
    public void removeById(final Long id) {
        super.removeById(id);
    }

    @Override
    public Reservation update(final Reservation entity) {
        return super.update(entity);
    }

    @Override
    public Optional<Reservation> getById(final Long id) {
        return super.getById(id);
    }

    @Override
    public Optional<Reservation> getByName(final String name) {
        return super.getByName(name);
    }

    @Override
    public List<Reservation> getAll() {
        return super.getAll();
    }
}
