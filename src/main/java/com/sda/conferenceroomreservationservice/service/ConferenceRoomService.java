package com.sda.conferenceroomreservationservice.service;

import com.sda.conferenceroomreservationservice.exception.type.ConferenceRoomNotFoundException;
import com.sda.conferenceroomreservationservice.mapper.ConferenceRoomMapper;
import com.sda.conferenceroomreservationservice.model.dto.ConferenceRoomDto;
import com.sda.conferenceroomreservationservice.model.entity.ConferenceRoom;
import com.sda.conferenceroomreservationservice.repository.ConferenceRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConferenceRoomService {

    private final ConferenceRoomRepository conferenceRoomRepository;

    public ConferenceRoomDto create(final ConferenceRoom conferenceRoom) {
        return ConferenceRoomMapper.map(conferenceRoomRepository.save(conferenceRoom));
    }

    public void remove(final ConferenceRoom conferenceRoom) {
        conferenceRoomRepository.delete(conferenceRoom);
    }

    public void removeById(final Long conferenceRoomId) {
        conferenceRoomRepository.deleteById(conferenceRoomId);
    }

    public ConferenceRoomDto update(final Long conferenceRoomId, final ConferenceRoom conferenceRoomFromRequest) {
        final ConferenceRoom conferenceRoomFromDatabase = getConferenceRoomByIdFromDatabase(conferenceRoomId);
        conferenceRoomFromDatabase.setName(conferenceRoomFromRequest.getName());
        conferenceRoomFromDatabase.setIdentifier(conferenceRoomFromRequest.getIdentifier());
        conferenceRoomFromDatabase.setLevel(conferenceRoomFromRequest.getLevel());
        conferenceRoomFromDatabase.setAvailability(conferenceRoomFromRequest.getAvailability());
        conferenceRoomFromDatabase.setNumberOfStandingPlaces(conferenceRoomFromRequest.getNumberOfStandingPlaces());
        conferenceRoomFromDatabase.setNumberOfSittingPlaces(conferenceRoomFromRequest.getNumberOfSittingPlaces());
        conferenceRoomFromDatabase.setReservationList(conferenceRoomFromRequest.getReservationList());
        conferenceRoomFromDatabase.setOrganisation(conferenceRoomFromRequest.getOrganisation());
        return ConferenceRoomMapper.map(conferenceRoomRepository.save(conferenceRoomFromDatabase));
    }

    public ConferenceRoomDto getById(final Long conferenceRoomId) {
        return ConferenceRoomMapper.map(getConferenceRoomByIdFromDatabase(conferenceRoomId));
    }

    public List<ConferenceRoomDto> getAll() {
        return conferenceRoomRepository.findAll()
                .stream()
                .map(ConferenceRoomMapper::map)
                .collect(Collectors.toList());
    }

    private ConferenceRoom getConferenceRoomByIdFromDatabase(final Long conferenceRoomId) {
        final Optional<ConferenceRoom> conferenceRoomFromDatabase = conferenceRoomRepository.findById(conferenceRoomId);
        return conferenceRoomFromDatabase.orElseThrow(ConferenceRoomNotFoundException::new);
    }
}
