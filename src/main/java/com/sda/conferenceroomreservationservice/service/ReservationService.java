package com.sda.conferenceroomreservationservice.service;

import com.sda.conferenceroomreservationservice.mapper.ConferenceRoomMapper;
import com.sda.conferenceroomreservationservice.mapper.ReservationMapper;
import com.sda.conferenceroomreservationservice.model.dto.ConferenceRoomDto;
import com.sda.conferenceroomreservationservice.model.dto.OrganisationDto;
import com.sda.conferenceroomreservationservice.model.dto.ReservationDto;
import com.sda.conferenceroomreservationservice.model.entity.Reservation;
import com.sda.conferenceroomreservationservice.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ReservationDto create(ReservationDto reservationDto) {
        repository.save(reservationMapper.map(reservationDto));
        return reservationDto;
    }

    public void removeById(final Long reservationId) {
        repository.delete(getFromDbById(reservationId));
    }

    public Reservation getFromDbById(final Long reservationId) {
        final Optional<Reservation> reservationFromDb = repository.findById(reservationId);
        return reservationFromDb.orElseThrow(/* EXCEPTION HERE */);
    }

    public ReservationDto update(final Long reservationId, final Reservation reservationFromRequest) {
        final Reservation reservationFromDb = getFromDbById(reservationId);
        reservationFromDb.setStartDateTime(reservationFromRequest.getStartDateTime());
        reservationFromDb.setEndDateTime(reservationFromRequest.getEndDateTime());
        reservationFromDb.setConferenceRoom(reservationFromRequest.getConferenceRoom());
        return reservationMapper.map(repository.save(reservationFromDb));
    }

    public List<ReservationDto> getAll() {
        return repository.findAll().stream()
                .map(reservationMapper::map)
                .collect(Collectors.toList());
    }

    public ReservationDto getById(final Long reservationId) {
        return reservationMapper.map(getFromDbById(reservationId));
    }
}
