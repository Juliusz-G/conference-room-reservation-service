package com.sda.conferenceroomreservationservice.mapper;

import com.sda.conferenceroomreservationservice.model.entity.ConferenceRoom;
import com.sda.conferenceroomreservationservice.model.entity.Reservation;
import com.sda.conferenceroomreservationservice.model.request.ReservationRequest;
import com.sda.conferenceroomreservationservice.model.response.ReservationResponse;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    public static Reservation map(final ReservationRequest reservationRequest) {
        final ConferenceRoom conferenceRoom = ConferenceRoom.builder()
                .id(reservationRequest.getConferenceRoomId())
                .build();
        return Reservation.builder()
                .startDateTime(reservationRequest.getStartDateTime())
                .endDateTime(reservationRequest.getEndDateTime())
                .conferenceRoom(conferenceRoom)
                .build();
    }

    public static ReservationResponse map(final Reservation reservation) {
        return ReservationResponse.builder()
                .id(reservation.getId())
                .startDateTime(reservation.getStartDateTime())
                .endDateTime(reservation.getEndDateTime())
                .conferenceRoomName(reservation.getConferenceRoom().getName())
                .organisationName(reservation.getConferenceRoom().getOrganisation().getName())
                .build();
    }
}
