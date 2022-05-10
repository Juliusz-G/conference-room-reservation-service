package com.sda.conferenceroomreservationservice.repository;

import com.sda.conferenceroomreservationservice.model.entity.Reservation;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends GenericRepository<Reservation> {
}
