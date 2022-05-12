package com.sda.conferenceroomreservationservice.repository;

import com.sda.conferenceroomreservationservice.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
