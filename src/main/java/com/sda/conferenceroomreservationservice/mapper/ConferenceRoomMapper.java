package com.sda.conferenceroomreservationservice.mapper;

import com.sda.conferenceroomreservationservice.model.entity.ConferenceRoom;
import com.sda.conferenceroomreservationservice.model.entity.Organisation;
import com.sda.conferenceroomreservationservice.model.request.ConferenceRoomRequest;
import com.sda.conferenceroomreservationservice.model.response.ConferenceRoomResponse;
import org.springframework.stereotype.Component;

@Component
public class ConferenceRoomMapper {

//    public static ConferenceRoom map(final ConferenceRoomRequest conferenceRoomRequest) {
//        final Organisation organisation = Organisation.builder()
//                .id(conferenceRoomRequest.getOrganisationId())
//                .build();
//        return ConferenceRoom.builder()
//                .name(conferenceRoomRequest.getConferenceRoomName())
//                .identifier(conferenceRoomRequest.getIdentifier())
//                .level(conferenceRoomRequest.getLevel())
//                .availability(conferenceRoomRequest.getAvailability())
//                .numberOfStandingPlaces(conferenceRoomRequest.getNumberOfStandingPlaces())
//                .numberOfSittingPlaces(conferenceRoomRequest.getNumberOfSittingPlaces())
//                .organisation(organisation)
//                .build();
//    }

    public static ConferenceRoom map(final ConferenceRoomRequest conferenceRoomRequest) {
        final Organisation organisation = Organisation.builder()
                .id(conferenceRoomRequest.getOrganisationId())
                .build();
        return ConferenceRoom.builder()
                .name(conferenceRoomRequest.getName())
                .identifier(conferenceRoomRequest.getIdentifier())
                .level(conferenceRoomRequest.getLevel())
                .availability(true)
                .numberOfStandingPlaces(conferenceRoomRequest.getNumberOfStandingPlaces())
                .numberOfSittingPlaces(conferenceRoomRequest.getNumberOfSittingPlaces())
                .organisation(organisation)
                .build();
    }

    public static ConferenceRoomResponse map(final ConferenceRoom conferenceRoom) {
        return ConferenceRoomResponse.builder()
                .id(conferenceRoom.getId())
                .name(conferenceRoom.getName())
                .identifier(conferenceRoom.getIdentifier())
                .level(conferenceRoom.getLevel())
                .availability(conferenceRoom.getAvailability())
                .numberOfStandingPlaces(conferenceRoom.getNumberOfStandingPlaces())
                .numberOfSittingPlaces(conferenceRoom.getNumberOfSittingPlaces())
                .organisationName(conferenceRoom.getOrganisation().getName())
                .build();
    }
}
