package com.sda.conferenceroomreservationservice.conferenceroom;

import com.sda.conferenceroomreservationservice.generics.genericrepository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceRoomRepository extends GenericRepository<ConferenceRoom> {
}
