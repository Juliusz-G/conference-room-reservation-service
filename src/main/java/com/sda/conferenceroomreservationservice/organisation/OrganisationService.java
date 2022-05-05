package com.sda.conferenceroomreservationservice.organisation;

import com.sda.conferenceroomreservationservice.generics.genericservice.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganisationService extends GenericServiceImpl<Organisation> {

    @Autowired
    public OrganisationService(OrganisationRepository repository) {
        super(repository);
    }

    @Override
    public Organisation create(Organisation entity) {
        return super.create(entity);
    }

    @Override
    public void remove(Organisation entity) {
        super.remove(entity);
    }

    @Override
    public void removeById(Long id) {
        super.removeById(id);
    }

    @Override
    public Organisation update(Organisation entity) {
        return super.update(entity);
    }

    @Override
    public Optional<Organisation> getById(Long id) {
        return super.getById(id);
    }

    @Override
    public Optional<Organisation> getByName(String name) {
        return super.getByName(name);
    }

    @Override
    public List<Organisation> getAll() {
        return super.getAll();
    }
}
