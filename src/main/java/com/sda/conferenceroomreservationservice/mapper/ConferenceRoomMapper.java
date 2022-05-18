package com.sda.conferenceroomreservationservice.mapper;

import com.sda.conferenceroomreservationservice.model.dto.ConferenceRoomDto;
import com.sda.conferenceroomreservationservice.model.dto.OrganisationDto;
import com.sda.conferenceroomreservationservice.model.dto.ReservationDto;
import com.sda.conferenceroomreservationservice.model.entity.ConferenceRoom;
import com.sda.conferenceroomreservationservice.model.entity.Organisation;
import com.sda.conferenceroomreservationservice.model.entity.Reservation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConferenceRoomMapper {

    public static ConferenceRoomDto map(final ConferenceRoom conferenceRoom) {
        final OrganisationDto organisationDto = OrganisationDto.builder()
                .id(conferenceRoom.getOrganisation().getId())
                .name(conferenceRoom.getOrganisation().getName())
                .description(conferenceRoom.getOrganisation().getDescription())
                .build();
        final List<ReservationDto> reservationDtos = conferenceRoom.getReservationList()
                .stream()
                .map(ReservationMapper::map)
                .collect(Collectors.toList());
        return ConferenceRoomDto.builder()
                .id(conferenceRoom.getId())
                .name(conferenceRoom.getName())
                .identifier(conferenceRoom.getIdentifier())
                .level(conferenceRoom.getLevel())
                .availability(conferenceRoom.getAvailability())
                .numberOfStandingPlaces(conferenceRoom.getNumberOfStandingPlaces())
                .numberOfSittingPlaces(conferenceRoom.getNumberOfSittingPlaces())
                .organisation(organisationDto)
                .reservationList(reservationDtos)
                .build();
    }

    public static ConferenceRoom map(final ConferenceRoomDto conferenceRoomDto) {
        final Organisation organisation = Organisation.builder()
                .id(conferenceRoomDto.getOrganisation().getId())
                .name(conferenceRoomDto.getOrganisation().getName())
                .description(conferenceRoomDto.getOrganisation().getDescription())
                .build();
        final List<Reservation> reservations = conferenceRoomDto.getReservationList()
                .stream()
                .map(ReservationMapper::map)
                .collect(Collectors.toList());
        return ConferenceRoom.builder()
                .id(conferenceRoomDto.getId())
                .name(conferenceRoomDto.getName())
                .identifier(conferenceRoomDto.getIdentifier())
                .level(conferenceRoomDto.getLevel())
                .availability(conferenceRoomDto.getAvailability())
                .numberOfStandingPlaces(conferenceRoomDto.getNumberOfStandingPlaces())
                .numberOfSittingPlaces(conferenceRoomDto.getNumberOfSittingPlaces())
                .organisation(organisation)
                .reservationList(reservations)
                .build();
    }
}
