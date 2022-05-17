package com.sda.conferenceroomreservationservice.controller;

import com.sda.conferenceroomreservationservice.exception.type.organisation.OrganisationNotFoundException;
import com.sda.conferenceroomreservationservice.model.dto.OrganisationDto;
import com.sda.conferenceroomreservationservice.service.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

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


    @DeleteMapping("/remove/{id}")
    public ResponseEntity<OrganisationDto> remove(@PathVariable("id") Long id, Principal principal) {
        organisationService.removeById(id, principal.getName());
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("/update")
    public ResponseEntity<OrganisationDto> update(OrganisationDto organisationDto, Principal principal) {
        return ResponseEntity.ok(organisationService.update(organisationDto, principal.getName()));
    }

    @GetMapping("/all/{name}")
    public ResponseEntity<OrganisationDto> getByName(@PathVariable String name, Principal principal) {
        if(!principal.getName().equals(name)){
            throw new OrganisationNotFoundException();
        }
        return ResponseEntity.ok(organisationService.getByName(name));
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrganisationDto>> getAll() {
        return ResponseEntity.ok(organisationService.getAll());
    }
}
