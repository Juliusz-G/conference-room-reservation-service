package com.sda.conferenceroomreservationservice.service;

import com.sda.conferenceroomreservationservice.exception.type.organisation.OrganisationNotFoundException;
import com.sda.conferenceroomreservationservice.mapper.ReservationMapper;
import com.sda.conferenceroomreservationservice.model.dto.ReservationDto;
import com.sda.conferenceroomreservationservice.model.entity.Organisation;
import com.sda.conferenceroomreservationservice.model.entity.Reservation;
import com.sda.conferenceroomreservationservice.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository repository;
    private final ReservationMapper reservationMapper;

    public ReservationService(ReservationRepository repository, ReservationMapper reservationMapper) {
        this.repository = repository;
        this.reservationMapper = reservationMapper;
    }

    public ReservationDto create(ReservationDto reservationDto, String organisationName) {
        validatePrincipal(reservationDto.getConferenceRoom().getOrganisation(), organisationName);
        repository.save(reservationMapper.map(reservationDto));
        return reservationDto;
    }

    public void removeById(final Long reservationId, String organisationName) {
        Reservation reservation = getFromDbById(reservationId);
        validatePrincipal(reservation.getConferenceRoom().getOrganisation(), organisationName);
        repository.delete(reservation);
    }

    private Reservation getFromDbById(final Long reservationId) {
        final Optional<Reservation> reservationFromDb = repository.findById(reservationId);
        return reservationFromDb.orElseThrow(/* EXCEPTION HERE */);
    }

    public ReservationDto update(final Long reservationId, final Reservation reservationFromRequest, String organisationName) {
        final Reservation reservationFromDb = getFromDbById(reservationId);
        validatePrincipal(reservationFromDb.getConferenceRoom().getOrganisation(), organisationName);
        reservationFromDb.setStartDateTime(reservationFromRequest.getStartDateTime());
        reservationFromDb.setEndDateTime(reservationFromRequest.getEndDateTime());
        reservationFromDb.setConferenceRoom(reservationFromRequest.getConferenceRoom());
        return reservationMapper.map(repository.save(reservationFromDb));
    }

    public List<ReservationDto> getAll(String organisationName) {
        return repository.findAll().stream()
                .filter(r -> r.getConferenceRoom()
                        .getOrganisation()
                        .getName()
                        .equals(organisationName))
                .map(reservationMapper::map)
                .collect(Collectors.toList());
    }

    public ReservationDto getById(final Long reservationId, String organisationName) {
        Reservation reservation = getFromDbById(reservationId);
        validatePrincipal(reservation.getConferenceRoom().getOrganisation(), organisationName);
        return reservationMapper.map(reservation);
    }

    private void validatePrincipal(Organisation organisation, String name) {
        if (!organisation.getName().equals(name)) {
            throw new OrganisationNotFoundException();
        }
    }
}
