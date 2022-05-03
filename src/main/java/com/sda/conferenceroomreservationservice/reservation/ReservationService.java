package com.sda.conferenceroomreservationservice.reservation;

import com.sda.conferenceroomreservationservice.GenericRepository;
import com.sda.conferenceroomreservationservice.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService extends GenericServiceImpl<Reservation> {

    @Autowired
    public ReservationService(GenericRepository<Reservation> repository) {
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
    public void update(Reservation entity) {
        super.update(entity);
    }

    @Override
    public Reservation getById(Long id) {
        return super.getById(id);
    }

    @Override
    public List<Reservation> getAll() {
        return super.getAll();
    }
}
