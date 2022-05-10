package com.sda.conferenceroomreservationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;

import java.util.Optional;

@NoRepositoryBean

public interface GenericRepository<T> extends JpaRepository<T, Long> {
    Optional<T> findByName(final String name);
}
