package com.sda.conferenceroomreservationservice.service;

import com.sda.conferenceroomreservationservice.exception.type.conferenceroom.ConferenceRoomNotFoundException;
import com.sda.conferenceroomreservationservice.exception.type.reservation.ReservationNotFoundException;
import com.sda.conferenceroomreservationservice.exception.type.reservation.ReservationPeriodNotFree;
import com.sda.conferenceroomreservationservice.mapper.ReservationMapper;
import com.sda.conferenceroomreservationservice.model.dto.ReservationDto;
import com.sda.conferenceroomreservationservice.model.entity.ConferenceRoom;
import com.sda.conferenceroomreservationservice.model.entity.Reservation;
import com.sda.conferenceroomreservationservice.repository.ConferenceRoomRepository;
import com.sda.conferenceroomreservationservice.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ConferenceRoomRepository conferenceRoomRepository;

    public ReservationDto create(final Reservation reservation) {
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findByIdAndAvailabilityEquals(reservation.getConferenceRoom().getId(), true)
                .orElseThrow(ConferenceRoomNotFoundException::new);
        reservation.setConferenceRoom(conferenceRoom);
        getReservationsForConferenceRoom(conferenceRoom).forEach(r -> isPeriodFree(reservation.getStartDateTime(), reservation.getEndDateTime(), r));
        return ReservationMapper.map(reservationRepository.save(reservation));
    }

    public ReservationDto getById(final Long reservationId) {
        return ReservationMapper.map(getReservationByIdFromDatabase(reservationId));
    }

    public List<ReservationDto> getAll() {
        return reservationRepository.findAll().stream()
                .map(ReservationMapper::map)
                .collect(Collectors.toList());
    }

    public ReservationDto updateById(
            final Long reservationId,
            final Reservation reservation
    ) {
        final Reservation reservationFromDatabase = getReservationByIdFromDatabase(reservationId);
        reservationFromDatabase.setStartDateTime(reservation.getStartDateTime());
        reservationFromDatabase.setEndDateTime(reservation.getEndDateTime());
        reservationFromDatabase.setConferenceRoom(reservation.getConferenceRoom());
        return create(reservationFromDatabase);
    }

    public void removeById(final Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    private Reservation getReservationByIdFromDatabase(final Long reservationId) {
        final Optional<Reservation> reservationFromDb = reservationRepository.findById(reservationId);
        return reservationFromDb.orElseThrow(ReservationNotFoundException::new);
    }

    private List<Reservation> getReservationsForConferenceRoom(final ConferenceRoom conferenceRoom) {
        return reservationRepository.findReservationsByConferenceRoom(conferenceRoom);
    }

    private void isPeriodFree(
            final LocalDateTime startOfReservation,
            final LocalDateTime endOfReservation,
            final Reservation reservation
    ) {
        if (isStartDateTimeNotFree(startOfReservation, reservation) ||
                isEndDateTimeNotFree(endOfReservation, reservation)) {
            throw new ReservationPeriodNotFree();
        }
    }

    private boolean isStartDateTimeNotFree(
            final LocalDateTime startOfReservation,
            final Reservation reservation
    ) {
        return (startOfReservation.isAfter(reservation.getStartDateTime()) ||
                startOfReservation.isEqual(reservation.getStartDateTime())
        ) && (startOfReservation.isBefore(reservation.getEndDateTime()) ||
                startOfReservation.isEqual(reservation.getEndDateTime())
        );
    }

    private boolean isEndDateTimeNotFree(
            final LocalDateTime endOfReservation,
            final Reservation reservation
    ) {
        return (endOfReservation.isAfter(reservation.getStartDateTime()) ||
                endOfReservation.isEqual(reservation.getStartDateTime())
        ) && (endOfReservation.isBefore(reservation.getEndDateTime()) ||
                endOfReservation.isEqual(reservation.getEndDateTime())
        );
    }
}
