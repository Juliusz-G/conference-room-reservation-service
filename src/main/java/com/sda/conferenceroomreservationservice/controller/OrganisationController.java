package com.sda.conferenceroomreservationservice.controller;

import com.sda.conferenceroomreservationservice.model.request.OrganisationRequest;
import com.sda.conferenceroomreservationservice.model.response.OrganisationResponse;
import com.sda.conferenceroomreservationservice.service.OrganisationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/organisations")
@RequiredArgsConstructor
public class OrganisationController {

    private final OrganisationService organisationService;

    @PostMapping
    public ResponseEntity<OrganisationResponse> create(@RequestBody @Valid final OrganisationRequest organisationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(organisationService.create(organisationRequest));
    }

    @GetMapping("/{organisationId}")
    public ResponseEntity<OrganisationResponse> getById(@PathVariable final Long organisationId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(organisationService.getById(organisationId));
    }

//    @GetMapping("/{organisationName}")
//    public ResponseEntity<OrganisationDto> getByName(@PathVariable final String organisationName) {
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(organisationService.getByName(organisationName));
//    }

    @GetMapping
    public ResponseEntity<List<OrganisationResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(organisationService.getAll());
    }

    @PutMapping("/{organisationId}")
    public ResponseEntity<OrganisationResponse> updateById(
            @PathVariable final Long organisationId,
            @RequestBody @Valid final OrganisationRequest organisationRequest
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(organisationService.updateById(organisationId, organisationRequest));
    }

    @DeleteMapping("/{organisationId}")
    public ResponseEntity<Void> remove(@PathVariable final Long organisationId) {
        organisationService.removeById(organisationId);
        return ResponseEntity.noContent()
                .build();
    }
}
