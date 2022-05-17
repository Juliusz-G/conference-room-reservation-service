package com.sda.conferenceroomreservationservice.service;

import com.sda.conferenceroomreservationservice.exception.type.conferenceroom.ConferenceRoomAlreadyExists;
import com.sda.conferenceroomreservationservice.exception.type.conferenceroom.ConferenceRoomNotFoundException;
import com.sda.conferenceroomreservationservice.exception.type.organisation.OrganisationNotFoundException;
import com.sda.conferenceroomreservationservice.mapper.ConferenceRoomMapper;
import com.sda.conferenceroomreservationservice.model.dto.ConferenceRoomDto;
import com.sda.conferenceroomreservationservice.model.entity.ConferenceRoom;
import com.sda.conferenceroomreservationservice.model.entity.Organisation;
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

    public ConferenceRoomDto create(final ConferenceRoom conferenceRoom, String organisationName) {
        validatePrincipal(conferenceRoom.getOrganisation(), organisationName);
        if (ExistsByName(conferenceRoom.getName()) || ExistsByIdentifier(conferenceRoom.getIdentifier())) {
            throw new ConferenceRoomAlreadyExists();
        } else {
            return ConferenceRoomMapper.map(conferenceRoomRepository.save(conferenceRoom));
        }
    }

    public void remove(final ConferenceRoom conferenceRoom, String organisationName) {
        validatePrincipal(conferenceRoom.getOrganisation(), organisationName);
        conferenceRoomRepository.delete(conferenceRoom);
    }

    public ConferenceRoomDto update(final ConferenceRoom conferenceRoomFromRequest, String organisationName) {
        final ConferenceRoom conferenceRoomFromDatabase = getConferenceRoomByIdFromDatabase(conferenceRoomFromRequest.getId());
        validatePrincipal(conferenceRoomFromRequest.getOrganisation(), organisationName);
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

    public ConferenceRoomDto getById(final Long conferenceRoomId, String organisationName) {
        ConferenceRoom conferenceRoom = getConferenceRoomByIdFromDatabase(conferenceRoomId);
        validatePrincipal(conferenceRoom.getOrganisation(), organisationName);
        return ConferenceRoomMapper.map(conferenceRoom);
    }

    public List<ConferenceRoomDto> getAll(String organisationName) {
        return conferenceRoomRepository.findAll()
                .stream()
                .filter(c -> c.getOrganisation().getName().equals(organisationName))
                .map(ConferenceRoomMapper::map)
                .collect(Collectors.toList());
    }

    public List<ConferenceRoomDto> getAllByOrganisationId(final Long organisationId, String organisationName) {
        return conferenceRoomRepository.findAllByOrganisationId(organisationId)
                .stream()
                .filter(c -> c.getOrganisation().getName().equals(organisationName))
                .map(ConferenceRoomMapper::map)
                .collect(Collectors.toList());
    }

    private ConferenceRoom getConferenceRoomByIdFromDatabase(final Long conferenceRoomId) {
        final Optional<ConferenceRoom> conferenceRoomFromDatabase = conferenceRoomRepository.findById(conferenceRoomId);
        return conferenceRoomFromDatabase.orElseThrow(ConferenceRoomNotFoundException::new);
    }

    private boolean ExistsByName(final String conferenceRoomName) {
        return conferenceRoomRepository.existsByName(conferenceRoomName);
    }

    private boolean ExistsByIdentifier(final String conferenceRoomName) {
        return conferenceRoomRepository.existsByIdentifier(conferenceRoomName);
    }

    private void validatePrincipal(Organisation organisation, String name) {
        if (!organisation.getName().equals(name)) {
            throw new OrganisationNotFoundException();
        }
    }
}
