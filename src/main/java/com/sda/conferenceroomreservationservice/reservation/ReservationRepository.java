package com.sda.conferenceroomreservationservice.reservation;

import com.sda.conferenceroomreservationservice.generics.genericrepository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends GenericRepository<Reservation> {
}
