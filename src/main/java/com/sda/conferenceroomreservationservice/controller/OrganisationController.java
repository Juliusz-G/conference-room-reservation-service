package com.sda.conferenceroomreservationservice.controller;

import com.sda.conferenceroomreservationservice.model.dto.OrganisationDto;
import com.sda.conferenceroomreservationservice.model.entity.Organisation;
import com.sda.conferenceroomreservationservice.service.OrganisationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/organisation")
@RequiredArgsConstructor
public class OrganisationController {

    private final OrganisationService organisationService;

    @PostMapping
    public ResponseEntity<OrganisationDto> create(@RequestBody @Valid final Organisation organisation) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(organisationService.create(organisation));
    }

    @GetMapping("/{organisationId}")
    public ResponseEntity<OrganisationDto> getById(@PathVariable final Long organisationId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(organisationService.getById(organisationId));
    }

    @GetMapping("/{organisationName}")
    public ResponseEntity<OrganisationDto> getByName(@PathVariable final String organisationName) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(organisationService.getByName(organisationName));
    }

    @GetMapping
    public ResponseEntity<List<OrganisationDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(organisationService.getAll());
    }

    @PutMapping("/{organisationId}")
    public ResponseEntity<OrganisationDto> updateById(
            @PathVariable final Long organisationId,
            @RequestBody @Valid final Organisation organisation
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(organisationService.updateById(organisationId, organisation));
    }

    @DeleteMapping("/{organisationId}")
    public ResponseEntity<OrganisationDto> remove(@PathVariable final Long organisationId) {
        organisationService.removeById(organisationId);
        return ResponseEntity.noContent()
                .build();
    }
}
