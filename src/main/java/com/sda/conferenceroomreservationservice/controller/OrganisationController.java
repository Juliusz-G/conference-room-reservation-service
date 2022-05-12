package com.sda.conferenceroomreservationservice.controller;

import com.sda.conferenceroomreservationservice.model.dto.OrganisationDto;
import com.sda.conferenceroomreservationservice.model.entity.Organisation;
import com.sda.conferenceroomreservationservice.service.OrganisationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/organisation")
public class OrganisationController{

    public OrganisationController(OrganisationService service) {
        super(service);
    }

    public ResponseEntity<OrganisationDto> create(OrganisationDto entity) {
        return super.create(entity);
    }

    public ResponseEntity<OrganisationDto> remove(OrganisationDto entity) {
        super.remove(entity);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<OrganisationDto> removeById(Long id) {
        super.removeById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<OrganisationDto> update(OrganisationDto entity) {
        return super.update(entity);
    }

    public ResponseEntity<Optional<OrganisationDto>> getById(Long id) {
        return super.getById(id);
    }

    public ResponseEntity<Optional<OrganisationDto>> getByName(String name) {
        return super.getByName(name);
    }

    public ResponseEntity<List<OrganisationDto>> getAll() {
        return super.getAll();
    }
}
