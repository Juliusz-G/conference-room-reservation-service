package com.sda.conferenceroomreservationservice.service;

import com.sda.conferenceroomreservationservice.exception.type.organisation.OrganisationAlreadyExists;
import com.sda.conferenceroomreservationservice.exception.type.organisation.OrganisationNotFoundException;
import com.sda.conferenceroomreservationservice.mapper.OrganisationMapper;
import com.sda.conferenceroomreservationservice.model.entity.Organisation;
import com.sda.conferenceroomreservationservice.model.request.OrganisationRequest;
import com.sda.conferenceroomreservationservice.model.response.OrganisationResponse;
import com.sda.conferenceroomreservationservice.repository.OrganisationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganisationService {

    private final OrganisationRepository organisationRepository;

    public OrganisationResponse create(final OrganisationRequest organisationRequest) {
        Organisation organisation = OrganisationMapper.map(organisationRequest);
        if (organisationRepository.findByName(organisation.getName()).isPresent()) {
            throw new OrganisationAlreadyExists();
        }
        return OrganisationMapper.map(organisationRepository.save(organisation));
    }

    public OrganisationResponse getById(final Long organisationId) {
        return OrganisationMapper.map(getOrganisationByIdFromDatabase(organisationId));
    }

    public List<OrganisationResponse> getAll() {
        return organisationRepository.findAll()
                .stream()
                .map(OrganisationMapper::map)
                .collect(Collectors.toList());
    }

    public OrganisationResponse updateById(
            final Long organisationId,
            final OrganisationRequest organisationRequest
    ) {
        final Organisation organisationFromDatabase = getOrganisationByIdFromDatabase(organisationId);
        organisationRepository.findByName(organisationFromDatabase.getName())
                .orElseThrow(OrganisationAlreadyExists::new);

        organisationFromDatabase.setName(organisationRequest.getName());
        organisationFromDatabase.setDescription(organisationRequest.getDescription());
        return OrganisationMapper.map(organisationRepository.save(organisationFromDatabase));
    }

    public void removeById(Long organisationId) {
        getOrganisationByIdFromDatabase(organisationId);
        organisationRepository.deleteById(organisationId);
    }

    private Organisation getOrganisationByIdFromDatabase(final Long organisationId) {
        final Optional<Organisation> organisationFromDatabase = organisationRepository.findById(organisationId);
        return organisationFromDatabase.orElseThrow(OrganisationNotFoundException::new);
    }
}
