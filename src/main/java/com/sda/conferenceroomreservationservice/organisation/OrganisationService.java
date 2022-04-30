package com.sda.conferenceroomreservationservice.organisation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganisationService {

    private final OrganisationRepository repository;

    @Autowired
    public OrganisationService(OrganisationRepository repository) {
        this.repository = repository;
    }
}
