package com.sda.conferenceroomreservationservice.service;

import com.sda.conferenceroomreservationservice.exception.type.organisation.OrganisationAlreadyExists;
import com.sda.conferenceroomreservationservice.exception.type.organisation.OrganisationNotFoundException;
import com.sda.conferenceroomreservationservice.mapper.OrganisationMapper;
import com.sda.conferenceroomreservationservice.model.dto.OrganisationDto;
import com.sda.conferenceroomreservationservice.model.entity.Organisation;
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

    public OrganisationDto create(final Organisation organisation) {
        if (organisationRepository.existsByName(organisation.getName())) {
            throw new OrganisationAlreadyExists();
        } else {
            return OrganisationMapper.map(organisationRepository.save(organisation));
        }
    }

    public OrganisationDto getById(final Long organisationId) {
        return OrganisationMapper.map(getOrganisationByIdFromDatabase(organisationId));
    }

    public OrganisationDto getByName(final String organisationName) {
        return Optional.of(OrganisationMapper.map(organisationRepository.findByName(organisationName)))
                .orElseThrow(OrganisationNotFoundException::new);
    }

    public List<OrganisationDto> getAll() {
        return organisationRepository.findAll()
                .stream()
                .map(OrganisationMapper::map)
                .collect(Collectors.toList());
    }

    public OrganisationDto updateById(
            final Long organisationId,
            final Organisation organisation
    ) {
        final Organisation organisationFromDatabase = getOrganisationByIdFromDatabase(organisationId);
        organisationFromDatabase.setName(organisation.getName());
        organisationFromDatabase.setDescription(organisation.getDescription());
        organisationFromDatabase.setRooms(organisation.getRooms());
        return OrganisationMapper.map(organisationRepository.save(organisationFromDatabase));
    }

    public void removeById(Long organisationId) {
        organisationRepository.deleteById(organisationId);
    }

    private Organisation getOrganisationByIdFromDatabase(final Long organisationId) {
        final Optional<Organisation> organisationFromDatabase = organisationRepository.findById(organisationId);
        return organisationFromDatabase.orElseThrow(OrganisationNotFoundException::new);
    }
}
