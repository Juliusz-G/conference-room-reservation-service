package com.sda.conferenceroomreservationservice.controller;

import com.sda.conferenceroomreservationservice.model.dto.OrganisationDto;
import com.sda.conferenceroomreservationservice.service.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/organisation")
public class OrganisationController {

    private final OrganisationService organisationService;

    @Autowired
    public OrganisationController(OrganisationService organisationService) {
        this.organisationService = organisationService;
    }

    public ResponseEntity<OrganisationDto> create() {
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<OrganisationDto> remove() {
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<OrganisationDto> removeById() {
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<OrganisationDto> update() {
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Optional<OrganisationDto>> getById() {
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Optional<OrganisationDto>> getByName() {
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<List<OrganisationDto>> getAll() {
        return ResponseEntity.noContent().build();
    }
}
