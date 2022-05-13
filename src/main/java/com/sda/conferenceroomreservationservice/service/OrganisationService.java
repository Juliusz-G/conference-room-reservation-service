package com.sda.conferenceroomreservationservice.service;

import com.sda.conferenceroomreservationservice.mapper.OrganisationMapper;
import com.sda.conferenceroomreservationservice.model.dto.OrganisationDto;
import com.sda.conferenceroomreservationservice.repository.OrganisationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrganisationService {

    private final OrganisationRepository repository;
    private final OrganisationMapper organisationMapper;

    public OrganisationService(OrganisationRepository repository, OrganisationMapper organisationMapper) {
        this.repository = repository;
        this.organisationMapper = organisationMapper;
    }

    public OrganisationDto create(OrganisationDto organisationDto) {
        repository.save(organisationMapper.map(organisationDto));
        return organisationDto;
    }

    public OrganisationDto remove(OrganisationDto organisationDto) {
        repository.delete(organisationMapper.map(organisationDto));
        return organisationDto;
    }

    public OrganisationDto update(OrganisationDto organisationDto) {
        repository.save(organisationMapper.map(organisationDto));
        return organisationDto;
    }

    public Optional<OrganisationDto> getByName(String name) {
        return Optional.of(organisationMapper.map(repository.findByName(name)));
    }

    public List<OrganisationDto> getAll() {
        return repository.findAll().stream()
                .map(organisationMapper::map)
                .collect(Collectors.toList());
    }
}
