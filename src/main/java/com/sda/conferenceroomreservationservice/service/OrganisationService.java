package com.sda.conferenceroomreservationservice.service;

import com.sda.conferenceroomreservationservice.exception.type.organisation.OrganisationAlreadyExists;
import com.sda.conferenceroomreservationservice.exception.type.organisation.OrganisationNotFoundException;
import com.sda.conferenceroomreservationservice.mapper.OrganisationMapper;
import com.sda.conferenceroomreservationservice.model.dto.OrganisationDto;
import com.sda.conferenceroomreservationservice.model.entity.Organisation;
import com.sda.conferenceroomreservationservice.repository.OrganisationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganisationService {

    private final OrganisationRepository repository;
    private final OrganisationMapper organisationMapper;

    public OrganisationService(OrganisationRepository repository, OrganisationMapper organisationMapper) {
        this.repository = repository;
        this.organisationMapper = organisationMapper;
    }

    public OrganisationDto create(final OrganisationDto organisationDto) {
        if (repository.existsByName(organisationDto.getName())){
            throw new OrganisationAlreadyExists();
        } else {
            repository.save(organisationMapper.map(organisationDto));
            return organisationDto;
        }
    }

    public OrganisationDto removeById(Long id, String name) {
        OrganisationDto organisationDto = organisationMapper.map(repository.findById(id).get());
        validatePrincipal(organisationDto, name);
        repository.delete(organisationMapper.map(organisationDto));
        return organisationDto;
    }

    public OrganisationDto update(OrganisationDto organisationDto, String name) {
        validatePrincipal(organisationDto, name);
        repository.save(organisationMapper.map(organisationDto));
        return organisationDto;
    }

    public OrganisationDto getByName(String name) {
        return organisationMapper.map(repository.findByName(name)
                .orElseThrow(OrganisationNotFoundException::new));
    }

    public List<OrganisationDto> getAll() {
        return repository.findAll().stream()
                .map(organisationMapper::map)
                .collect(Collectors.toList());
    }

    private void validatePrincipal(OrganisationDto organisationDto, String name) {
        if (!organisationDto.getName().equals(name)) {
            throw new OrganisationNotFoundException();
        }
    }
}
