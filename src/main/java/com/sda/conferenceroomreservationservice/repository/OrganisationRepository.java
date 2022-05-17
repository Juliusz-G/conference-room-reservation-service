package com.sda.conferenceroomreservationservice.repository;

import com.sda.conferenceroomreservationservice.model.entity.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation, Long> {
    Optional<Organisation> findByName(String name);
    boolean existsByName(String name);
}
