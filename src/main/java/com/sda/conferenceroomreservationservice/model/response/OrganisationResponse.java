package com.sda.conferenceroomreservationservice.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class OrganisationResponse {

    private final Long id;
    private final String name;
    private final String description;
    private final List<String> conferenceRooms;
}
