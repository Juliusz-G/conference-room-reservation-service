package com.sda.conferenceroomreservationservice.model.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ConferenceRoomDto {

    private final Long id;
    private final String name;
    private final String identifier;
    private final Integer level;
    private final Boolean availability;
    private final Integer numberOfPlaces;
}
