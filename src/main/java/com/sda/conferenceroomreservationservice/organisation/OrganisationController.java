package com.sda.conferenceroomreservationservice.organisation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organisation")
public class OrganisationController {

    private final OrganisationService service;

    @Autowired
    public OrganisationController(OrganisationService service) {
        this.service = service;
    }
}
