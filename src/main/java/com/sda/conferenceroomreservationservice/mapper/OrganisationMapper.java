package com.sda.conferenceroomreservationservice.mapper;

import com.sda.conferenceroomreservationservice.model.dto.OrganisationDto;
import com.sda.conferenceroomreservationservice.model.entity.Organisation;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrganisationMapper {

    public OrganisationDto map(Organisation organisation) {
        return OrganisationDto.builder()
                .id(organisation.getId())
                .name(organisation.getName())
                .description(organisation.getDescription())
                .rooms(organisation.getRooms().stream()
                        .map(ConferenceRoomMapper::map)
                        .collect(Collectors.toList()))
                .build();
    }

    public Organisation map(OrganisationDto organisationDto) {
        return Organisation.builder()
                .id(organisationDto.getId())
                .name(organisationDto.getName())
                .description(organisationDto.getDescription())
                .build();
    }
}
