package com.sda.conferenceroomreservationservice.controller;

import com.sda.conferenceroomreservationservice.model.dto.OrganisationDto;
import com.sda.conferenceroomreservationservice.service.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("/create")
    public ResponseEntity<OrganisationDto> create(@RequestBody @Valid final OrganisationDto organisationDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(organisationService.create(organisationDto));
    }


    @DeleteMapping("/remove")
    public ResponseEntity<OrganisationDto> remove(@RequestBody @Valid final OrganisationDto organisationDto) {
        return ResponseEntity.ok(organisationService.remove(organisationDto));
    }

    @PutMapping("/update")
    public ResponseEntity<OrganisationDto> update(OrganisationDto organisationDto) {
        return ResponseEntity.ok(organisationService.update(organisationDto));
    }

    @GetMapping("/all/{name}")
    public ResponseEntity<OrganisationDto> getByName(@PathVariable String name) {
        return ResponseEntity.ok(organisationService.getByName(name));
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrganisationDto>> getAll() {
        return ResponseEntity.ok(organisationService.getAll());
    }
}
