package com.sda.conferenceroomreservationservice.mapper;

import com.sda.conferenceroomreservationservice.model.dto.OrganisationDto;
import com.sda.conferenceroomreservationservice.model.dto.ReservationDto;
import com.sda.conferenceroomreservationservice.model.entity.Organisation;
import com.sda.conferenceroomreservationservice.model.entity.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {
    public ReservationDto map(Reservation reservation) {
        return ReservationDto.builder()
                .id(reservation.getId())
                .startDateTime(reservation.getStartDateTime())
                .endDateTime(reservation.getEndDateTime())
                .conferenceRoom(reservation.getConferenceRoom())
                .build();
    }

    public Reservation map(ReservationDto reservationDto) {
        return Reservation.builder()
                .id(reservationDto.getId())
                .startDateTime(reservationDto.getStartDateTime())
                .endDateTime(reservationDto.getEndDateTime())
                .conferenceRoom(reservationDto.getConferenceRoom())
                .build();
    }
}
