package com.sda.conferenceroomreservationservice.service;

import com.sda.conferenceroomreservationservice.mapper.OrganisationMapper;
import com.sda.conferenceroomreservationservice.model.dto.OrganisationDto;
import com.sda.conferenceroomreservationservice.model.entity.Organisation;
import com.sda.conferenceroomreservationservice.repository.OrganisationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganisationService {

    private final OrganisationRepository repository;
    private final OrganisationMapper organisationMapper;

    public OrganisationService(OrganisationRepository repository, OrganisationMapper organisationMapper) {
        this.repository = repository;
        this.organisationMapper = organisationMapper;
    }

}
