package com.sda.conferenceroomreservationservice.organisation;

import com.sda.conferenceroomreservationservice.GenericRepository;
import com.sda.conferenceroomreservationservice.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganisationService extends GenericServiceImpl<Organisation> {

    @Autowired
    public OrganisationService(GenericRepository<Organisation> repository) {
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
    public void update(Organisation entity) {
        super.update(entity);
    }

    @Override
    public Organisation getById(Long id) {
        return super.getById(id);
    }

    @Override
    public List<Organisation> getAll() {
        return super.getAll();
    }
}
