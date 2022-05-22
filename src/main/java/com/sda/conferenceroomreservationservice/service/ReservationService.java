package com.sda.conferenceroomreservationservice.service;

import com.sda.conferenceroomreservationservice.exception.type.conferenceroom.ConferenceRoomNotFoundException;
import com.sda.conferenceroomreservationservice.exception.type.reservation.ReservationNotFoundException;
import com.sda.conferenceroomreservationservice.exception.type.reservation.ReservationPeriodNotFree;
import com.sda.conferenceroomreservationservice.mapper.ReservationMapper;
import com.sda.conferenceroomreservationservice.model.entity.ConferenceRoom;
import com.sda.conferenceroomreservationservice.model.entity.Reservation;
import com.sda.conferenceroomreservationservice.model.request.ReservationRequest;
import com.sda.conferenceroomreservationservice.model.response.ReservationResponse;
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

    public ReservationResponse create(final ReservationRequest reservationRequest) {
        Reservation reservation = ReservationMapper.map(reservationRequest);
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findByIdAndAvailabilityEquals(
                reservation.getConferenceRoom().getId(),
                true
        ).orElseThrow(ConferenceRoomNotFoundException::new);
        getReservationsForConferenceRoom(conferenceRoom).forEach(r ->
                isPeriodFree(
                        reservationRequest.getStartDateTime(),
                        reservationRequest.getEndDateTime(),
                        r
                ));
        reservation.setConferenceRoom(conferenceRoom);
        return ReservationMapper.map(reservationRepository.save(reservation));
    }

    public ReservationResponse getById(final Long reservationId) {
        return ReservationMapper.map(getReservationByIdFromDatabase(reservationId));
    }

    public List<ReservationResponse> getAll() {
        return reservationRepository.findAll().stream()
                .map(ReservationMapper::map)
                .collect(Collectors.toList());
    }

    public ReservationResponse updateById(
            final Long reservationId,
            final ReservationRequest reservationRequest
    ) {
        final Reservation reservationFromDatabase = getReservationByIdFromDatabase(reservationId);
        final ConferenceRoom conferenceRoom = conferenceRoomRepository.findById(reservationRequest.getConferenceRoomId())
                .orElseThrow(ConferenceRoomNotFoundException::new);
        getReservationsForConferenceRoom(conferenceRoom)
                .stream().filter(r -> !r.getId().equals(reservationId))
                .forEach(r ->
                isPeriodFree(
                        reservationRequest.getStartDateTime(),
                        reservationRequest.getEndDateTime(),
                        r
                ));
        reservationFromDatabase.setStartDateTime(reservationRequest.getStartDateTime());
        reservationFromDatabase.setEndDateTime(reservationRequest.getEndDateTime());
        reservationFromDatabase.setConferenceRoom(conferenceRoom);
        return ReservationMapper.map(reservationRepository.save(reservationFromDatabase));
    }

    public void removeById(final Long reservationId) {
        getReservationByIdFromDatabase(reservationId);
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
