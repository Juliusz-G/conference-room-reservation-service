package com.sda.conferenceroomreservationservice.mapper;

import com.sda.conferenceroomreservationservice.model.dto.ConferenceRoomDto;
import com.sda.conferenceroomreservationservice.model.dto.OrganisationDto;
import com.sda.conferenceroomreservationservice.model.dto.ReservationDto;
import com.sda.conferenceroomreservationservice.model.entity.ConferenceRoom;
import com.sda.conferenceroomreservationservice.model.entity.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    public static ReservationDto map(final Reservation reservation) {
        final OrganisationDto organisationDto = OrganisationDto.builder()
                .id(reservation.getConferenceRoom().getOrganisation().getId())
                .name(reservation.getConferenceRoom().getOrganisation().getName())
                .description(reservation.getConferenceRoom().getOrganisation().getDescription())
                .build();
        final ConferenceRoomDto conferenceRoomDto = ConferenceRoomDto.builder()
                .id(reservation.getConferenceRoom().getId())
                .name(reservation.getConferenceRoom().getName())
                .identifier(reservation.getConferenceRoom().getIdentifier())
                .level(reservation.getConferenceRoom().getLevel())
                .availability(reservation.getConferenceRoom().getAvailability())
                .numberOfStandingPlaces(reservation.getConferenceRoom().getNumberOfStandingPlaces())
                .numberOfSittingPlaces(reservation.getConferenceRoom().getNumberOfSittingPlaces())
                .organisation(organisationDto)
                .build();
        return ReservationDto.builder()
                .id(reservation.getId())
                .startDateTime(reservation.getStartDateTime())
                .endDateTime(reservation.getEndDateTime())
                .conferenceRoom(conferenceRoomDto)
                .build();
    }

    public static Reservation map(final ReservationDto reservationDto) {
        final ConferenceRoom conferenceRoom = ConferenceRoom.builder()
                .id(reservationDto.getConferenceRoom().getId())
                .name(reservationDto.getConferenceRoom().getName())
                .identifier(reservationDto.getConferenceRoom().getIdentifier())
                .level(reservationDto.getConferenceRoom().getLevel())
                .availability(reservationDto.getConferenceRoom().getAvailability())
                .numberOfStandingPlaces(reservationDto.getConferenceRoom().getNumberOfStandingPlaces())
                .numberOfSittingPlaces(reservationDto.getConferenceRoom().getNumberOfSittingPlaces())
                .build();
        return Reservation.builder()
                .id(reservationDto.getId())
                .startDateTime(reservationDto.getStartDateTime())
                .endDateTime(reservationDto.getEndDateTime())
                .conferenceRoom(conferenceRoom)
                .build();
    }
}
