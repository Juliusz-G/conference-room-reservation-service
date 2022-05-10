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
                .numberOfPlaces(conferenceRoom.getNumberOfPlaces())
                .build();
    }
}
