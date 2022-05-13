package com.sda.conferenceroomreservationservice.repository;

import com.sda.conferenceroomreservationservice.model.entity.ConferenceRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConferenceRoomRepository extends JpaRepository<ConferenceRoom, Long> {
    List<ConferenceRoom> findAllByOrganisationId(final Long organisationId);
    Optional<ConferenceRoom> findByName(String name);
}
