package com.sda.conferenceroomreservationservice.service;

import com.sda.conferenceroomreservationservice.exception.type.conferenceroom.ConferenceRoomAlreadyExists;
import com.sda.conferenceroomreservationservice.exception.type.conferenceroom.ConferenceRoomNotFoundException;
import com.sda.conferenceroomreservationservice.exception.type.organisation.OrganisationNotFoundException;
import com.sda.conferenceroomreservationservice.mapper.ConferenceRoomMapper;
import com.sda.conferenceroomreservationservice.model.dto.ConferenceRoomDto;
import com.sda.conferenceroomreservationservice.model.entity.ConferenceRoom;
import com.sda.conferenceroomreservationservice.model.entity.Organisation;
import com.sda.conferenceroomreservationservice.repository.ConferenceRoomRepository;
import com.sda.conferenceroomreservationservice.repository.OrganisationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConferenceRoomService {

    private final ConferenceRoomRepository conferenceRoomRepository;
    private final OrganisationRepository organisationRepository;

    public ConferenceRoomDto create(final ConferenceRoom conferenceRoom) {
        Organisation organisation = organisationRepository.findById(conferenceRoom.getOrganisation().getId())
                .orElseThrow(OrganisationNotFoundException::new);
        conferenceRoom.setOrganisation(organisation);
        if (existsByName(conferenceRoom.getName()) || existsByIdentifier(conferenceRoom.getIdentifier())) {
            throw new ConferenceRoomAlreadyExists();
        } else {
            return ConferenceRoomMapper.map(conferenceRoomRepository.save(conferenceRoom));
        }
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

    public List<ConferenceRoomDto> getAllByOrganisationId(final Long organisationId) {
        return conferenceRoomRepository.findAllByOrganisationId(organisationId)
                .stream()
                .map(ConferenceRoomMapper::map)
                .collect(Collectors.toList());
    }

    public ConferenceRoomDto updateById(
            final Long conferenceRoomId,
            final ConferenceRoom conferenceRoomFromRequest
    ) {
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

    public void removeById(final Long conferenceRoomId) {
        conferenceRoomRepository.deleteById(conferenceRoomId);
    }

    private ConferenceRoom getConferenceRoomByIdFromDatabase(final Long conferenceRoomId) {
        final Optional<ConferenceRoom> conferenceRoomFromDatabase = conferenceRoomRepository.findById(conferenceRoomId);
        return conferenceRoomFromDatabase.orElseThrow(ConferenceRoomNotFoundException::new);
    }

    private boolean existsByName(final String conferenceRoomName) {
        return conferenceRoomRepository.existsByName(conferenceRoomName);
    }

    private boolean existsByIdentifier(final String conferenceRoomName) {
        return conferenceRoomRepository.existsByIdentifier(conferenceRoomName);
    }
}
