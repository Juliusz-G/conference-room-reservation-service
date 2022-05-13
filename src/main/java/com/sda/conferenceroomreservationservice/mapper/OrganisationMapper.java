package com.sda.conferenceroomreservationservice.mapper;

import com.sda.conferenceroomreservationservice.model.dto.OrganisationDto;
import com.sda.conferenceroomreservationservice.model.entity.Organisation;
import org.springframework.stereotype.Component;

@Component
public class OrganisationMapper {
    public OrganisationDto map(Organisation organisation) {
        return OrganisationDto.builder()
                .name(organisation.getName())
                .description(organisation.getDescription())
               // .rooms(organisation.getRooms())
                .build();
    }

    public Organisation map(OrganisationDto organisationDto) {
        return Organisation.builder()
                .name(organisationDto.getName())
                .description(organisationDto.getDescription())
              //  .rooms(organisationDto.getRooms())
                .build();
    }
}
