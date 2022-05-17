package com.sda.conferenceroomreservationservice.model.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OrganisationDto {

    private final Long id;
    private final String name;
    private final String description;
    private final List<ConferenceRoomDto> rooms;
    private final String password;
}
