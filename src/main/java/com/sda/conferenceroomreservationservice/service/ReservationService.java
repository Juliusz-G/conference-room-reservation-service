package com.sda.conferenceroomreservationservice.service;

import com.sda.conferenceroomreservationservice.exception.type.conferenceroom.ConferenceRoomNotFoundException;
import com.sda.conferenceroomreservationservice.exception.type.reservation.ReservationCreationException;
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

    // Create

    public ReservationDto create(final Reservation reservation) {
        // TODO: Validation
//        ConferenceRoom conferenceRoom = conferenceRoomRepository.findById(reservation.getConferenceRoom().getId())
//                .orElseThrow(ConferenceRoomNotFoundException::new);
//        reservation.setConferenceRoom(conferenceRoom);
//        final List<Reservation> reservationsForConferenceRoom = getReservationsForConferenceRoom(reservation.getConferenceRoom());
//        boolean isThereFreePeriod = reservationsForConferenceRoom.stream()
//                .noneMatch(r -> isPeriodFree(reservation.getStartDateTime(), reservation.getEndDateTime(), r));
//        if (isThereFreePeriod) {
//            return ReservationMapper.map(reservationRepository.save(reservation));
//        }
//        throw new ReservationCreationException();
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findById(reservation.getConferenceRoom().getId())
                .orElseThrow(ConferenceRoomNotFoundException::new);
        reservation.setConferenceRoom(conferenceRoom);
        return ReservationMapper.map(reservationRepository.save(reservation));
    }

    // Read

    public ReservationDto getById(final Long reservationId) {
        return ReservationMapper.map(getReservationByIdFromDatabase(reservationId));
    }

    public List<ReservationDto> getAll() {
        return reservationRepository.findAll().stream()
                .map(ReservationMapper::map)
                .collect(Collectors.toList());
    }

    // Update

//    public ReservationDto update(final Reservation reservation) {
//        final Reservation reservationFromDatabase = getReservationByIdFromDatabase(reservation.getId());
//        reservationFromDatabase.setStartDateTime(reservation.getStartDateTime());
//        reservationFromDatabase.setEndDateTime(reservation.getEndDateTime());
//        reservationFromDatabase.setConferenceRoom(reservation.getConferenceRoom());
//        return create(reservationFromDatabase);
//    }

    public ReservationDto updateById(final Long reservationId, final Reservation reservation) {
        final Reservation reservationFromDatabase = getReservationByIdFromDatabase(reservationId);
        reservationFromDatabase.setStartDateTime(reservation.getStartDateTime());
        reservationFromDatabase.setEndDateTime(reservation.getEndDateTime());
        reservationFromDatabase.setConferenceRoom(reservation.getConferenceRoom());
        return create(reservationFromDatabase);
    }

    // Delete

//    public void remove(final Reservation reservation) {
//        reservationRepository.delete(reservation);
//    }

    public void removeById(final Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    // External

    private Reservation getReservationByIdFromDatabase(final Long reservationId) {
        final Optional<Reservation> reservationFromDb = reservationRepository.findById(reservationId);
        return reservationFromDb.orElseThrow(ReservationNotFoundException::new);
    }

    private List<Reservation> getReservationsForConferenceRoom(final ConferenceRoom conferenceRoom) {
        return reservationRepository.findReservationsByConferenceRoom(conferenceRoom);
    }

    private boolean isPeriodFree(final LocalDateTime startOfReservation, final LocalDateTime endOfReservation, final Reservation reservation) {
        if (isStartDateTimeFree(startOfReservation, reservation) && isEndDateTimeFree(endOfReservation, reservation)) {
            return true;
        } else {
            throw new ReservationPeriodNotFree();
        }
    }

    private boolean isStartDateTimeFree(final LocalDateTime startOfReservation, final Reservation reservation) {
        return startOfReservation.isAfter(reservation.getStartDateTime()) && startOfReservation.isBefore(reservation.getEndDateTime());
    }

    private boolean isEndDateTimeFree(final LocalDateTime endOfReservation, final Reservation reservation) {
        return endOfReservation.isAfter(reservation.getStartDateTime()) && endOfReservation.isBefore(reservation.getEndDateTime());
    }
}
