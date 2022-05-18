package com.sda.conferenceroomreservationservice.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class OrganisationDto {

    private final Long id;
    private final String name;
    private final String description;
    @Builder.Default
    private final List<ConferenceRoomDto> rooms = new ArrayList<>();
}
