package com.sda.conferenceroomreservationservice.mapper;

import com.sda.conferenceroomreservationservice.model.dto.ConferenceRoomDto;
import com.sda.conferenceroomreservationservice.model.entity.ConferenceRoom;

public class ConferenceRoomMapper {

    public static ConferenceRoomDto map(final ConferenceRoom conferenceRoom) {
        return ConferenceRoomDto.builder()
                .id(conferenceRoom.getId())
                .name(conferenceRoom.getName())
                .identifier(conferenceRoom.getIdentifier())
                .level(conferenceRoom.getLevel())
                .availability(conferenceRoom.getAvailability())
                .numberOfStandingPlaces(conferenceRoom.getNumberOfStandingPlaces())
                .numberOfSittingPlaces(conferenceRoom.getNumberOfSittingPlaces())
                .reservationList(conferenceRoom.getReservationList())
                .organisation(conferenceRoom.getOrganisation())
                .build();
    }

    public static ConferenceRoom map(final ConferenceRoomDto conferenceRoomDto) {
        return ConferenceRoom.builder()
                .id(conferenceRoomDto.getId())
                .name(conferenceRoomDto.getName())
                .identifier(conferenceRoomDto.getIdentifier())
                .level(conferenceRoomDto.getLevel())
                .availability(conferenceRoomDto.getAvailability())
                .numberOfStandingPlaces(conferenceRoomDto.getNumberOfStandingPlaces())
                .numberOfSittingPlaces(conferenceRoomDto.getNumberOfSittingPlaces())
                .reservationList(conferenceRoomDto.getReservationList())
                .organisation(conferenceRoomDto.getOrganisation())
                .build();
    }
}
