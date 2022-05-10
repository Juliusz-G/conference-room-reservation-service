package com.sda.conferenceroomreservationservice.service;

import com.sda.conferenceroomreservationservice.model.entity.Organisation;
import com.sda.conferenceroomreservationservice.repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganisationService extends GenericServiceImpl<Organisation> {

    @Autowired
    public OrganisationService(final OrganisationRepository repository) {
        super(repository);
    }

    @Override
    public Organisation create(final Organisation entity) {
        return super.create(entity);
    }

    @Override
    public void remove(final Organisation entity) {
        super.remove(entity);
    }

    @Override
    public void removeById(final Long id) {
        super.removeById(id);
    }

    @Override
    public Organisation update(final Organisation entity) {
        return super.update(entity);
    }

    @Override
    public Optional<Organisation> getById(final Long id) {
        return super.getById(id);
    }

    @Override
    public Optional<Organisation> getByName(final String name) {
        return super.getByName(name);
    }

    @Override
    public List<Organisation> getAll() {
        return super.getAll();
    }
}
