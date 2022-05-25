package com.sda.conferenceroomreservationservice.service;

import com.sda.conferenceroomreservationservice.exception.type.conferenceroom.ConferenceRoomAlreadyExists;
import com.sda.conferenceroomreservationservice.exception.type.conferenceroom.ConferenceRoomNotFoundException;
import com.sda.conferenceroomreservationservice.exception.type.organisation.OrganisationNotFoundException;
import com.sda.conferenceroomreservationservice.mapper.ConferenceRoomMapper;
import com.sda.conferenceroomreservationservice.model.entity.ConferenceRoom;
import com.sda.conferenceroomreservationservice.model.entity.Organisation;
import com.sda.conferenceroomreservationservice.model.request.ConferenceRoomRequest;
import com.sda.conferenceroomreservationservice.model.response.ConferenceRoomResponse;
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

    public ConferenceRoomResponse create(final ConferenceRoomRequest conferenceRoomRequest) {
        ConferenceRoom conferenceRoom = ConferenceRoomMapper.map(conferenceRoomRequest);
        conferenceRoomRepository.findByName(conferenceRoom.getName()).ifPresent(room -> {
            throw new ConferenceRoomAlreadyExists();
        });
        Organisation organisation = organisationRepository.findById(conferenceRoomRequest.getOrganisationId())
                .orElseThrow(() -> {
                    throw new OrganisationNotFoundException();
                });
        conferenceRoom.setOrganisation(organisation);
        return ConferenceRoomMapper.map(conferenceRoomRepository.save(conferenceRoom));

    }

    public ConferenceRoomResponse getById(final Long conferenceRoomId) {
        return ConferenceRoomMapper.map(getConferenceRoomByIdFromDatabase(conferenceRoomId));
    }

    public List<ConferenceRoomResponse> getAll() {
        return conferenceRoomRepository.findAll()
                .stream()
                .map(ConferenceRoomMapper::map)
                .collect(Collectors.toList());
    }

    public List<ConferenceRoomResponse> getAllByOrganisationId(final Long organisationId) {
        return conferenceRoomRepository.findAllByOrganisationId(organisationId)
                .stream()
                .map(ConferenceRoomMapper::map)
                .collect(Collectors.toList());
    }

    public ConferenceRoomResponse updateById(
            final Long conferenceRoomId,
            final ConferenceRoomRequest conferenceRoomRequest
    ) {
        final ConferenceRoom conferenceRoomFromDatabase = getConferenceRoomByIdFromDatabase(conferenceRoomId);

        conferenceRoomFromDatabase.setName(conferenceRoomRequest.getName());
        conferenceRoomFromDatabase.setIdentifier(conferenceRoomRequest.getIdentifier());
        conferenceRoomFromDatabase.setLevel(conferenceRoomRequest.getLevel());
        conferenceRoomFromDatabase.setAvailability(conferenceRoomRequest.getAvailability());
        conferenceRoomFromDatabase.setNumberOfStandingPlaces(conferenceRoomRequest.getNumberOfStandingPlaces());
        conferenceRoomFromDatabase.setNumberOfSittingPlaces(conferenceRoomRequest.getNumberOfSittingPlaces());
        return ConferenceRoomMapper.map(conferenceRoomRepository.save(conferenceRoomFromDatabase));
    }

    public void removeById(final Long conferenceRoomId) {
        getConferenceRoomByIdFromDatabase(conferenceRoomId);
        conferenceRoomRepository.deleteById(conferenceRoomId);
    }

    private ConferenceRoom getConferenceRoomByIdFromDatabase(final Long conferenceRoomId) {
        final Optional<ConferenceRoom> conferenceRoomFromDatabase = conferenceRoomRepository.findById(conferenceRoomId);
        return conferenceRoomFromDatabase.orElseThrow(ConferenceRoomNotFoundException::new);
    }
}
