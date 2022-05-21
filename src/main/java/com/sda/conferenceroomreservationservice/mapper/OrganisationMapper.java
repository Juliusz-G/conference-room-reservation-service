package com.sda.conferenceroomreservationservice.mapper;

import com.sda.conferenceroomreservationservice.model.dto.ConferenceRoomDto;
import com.sda.conferenceroomreservationservice.model.dto.OrganisationDto;
import com.sda.conferenceroomreservationservice.model.entity.ConferenceRoom;
import com.sda.conferenceroomreservationservice.model.entity.Organisation;
import com.sda.conferenceroomreservationservice.model.request.OrganisationRequest;
import com.sda.conferenceroomreservationservice.model.response.OrganisationResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrganisationMapper {

//    public static OrganisationDto map(final Organisation organisation) {
//        List<ConferenceRoomDto> conferenceRoomDtos = new ArrayList<>();
//        if (organisation.getRooms() != null) {
//            conferenceRoomDtos = organisation.getRooms()
//                    .stream()
//                    .map(ConferenceRoomMapper::map)
//                    .collect(Collectors.toList());
//        }
//        return OrganisationDto.builder()
//                .id(organisation.getId())
//                .name(organisation.getName())
//                .description(organisation.getDescription())
//                .rooms(conferenceRoomDtos)
//                .build();
//    }
//
//    public static Organisation map(final OrganisationDto organisationDto) {
//        List<ConferenceRoom> conferenceRooms = organisationDto.getRooms()
//                .stream()
//                .map(ConferenceRoomMapper::map)
//                .collect(Collectors.toList());
//        return Organisation.builder()
//                .id(organisationDto.getId())
//                .name(organisationDto.getName())
//                .description(organisationDto.getDescription())
//                .rooms(conferenceRooms)
//                .build();
//    }

    public static Organisation map(final OrganisationRequest organisationRequest) {
        return Organisation.builder()
                .name(organisationRequest.getName())
                .description(organisationRequest.getDescription())
                .build();
    }

    public static OrganisationResponse map(final Organisation organisation) {
        return OrganisationResponse.builder()
                .id(organisation.getId())
                .name(organisation.getName())
                .description(organisation.getDescription())
                .build();
    }
}
