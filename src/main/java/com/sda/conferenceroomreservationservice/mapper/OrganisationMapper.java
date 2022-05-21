package com.sda.conferenceroomreservationservice.mapper;

import com.sda.conferenceroomreservationservice.model.entity.ConferenceRoom;
import com.sda.conferenceroomreservationservice.model.entity.Organisation;
import com.sda.conferenceroomreservationservice.model.request.OrganisationRequest;
import com.sda.conferenceroomreservationservice.model.response.OrganisationResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrganisationMapper {

    public static Organisation map(final OrganisationRequest organisationRequest) {
        return Organisation.builder()
                .name(organisationRequest.getName())
                .description(organisationRequest.getDescription())
                .build();
    }

    public static OrganisationResponse map(final Organisation organisation) {
        List<String> conferenceRooms = organisation.getRooms()
                .stream()
                .map(ConferenceRoom::getName)
                .collect(Collectors.toList());
        return OrganisationResponse.builder()
                .id(organisation.getId())
                .name(organisation.getName())
                .description(organisation.getDescription())
                .conferenceRooms(conferenceRooms)
                .build();
    }
}
