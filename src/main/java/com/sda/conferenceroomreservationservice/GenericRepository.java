package com.sda.conferenceroomreservationservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface GenericRepository<T> extends JpaRepository<T, Long> {
}
