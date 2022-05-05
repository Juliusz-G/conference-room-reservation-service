package com.sda.conferenceroomreservationservice.organisation;

import com.sda.conferenceroomreservationservice.generics.genericcontroller.GenericControllerImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/organisation")
public class OrganisationController extends GenericControllerImpl<Organisation> {

    public OrganisationController(OrganisationService service) {
        super(service);
    }

    @Override
    public ResponseEntity<Organisation> create(Organisation entity) {
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
    public ResponseEntity<Organisation> update(Organisation entity) {
        return super.update(entity);
    }

    @Override
    public ResponseEntity<Optional<Organisation>> getById(Long id) {
        return super.getById(id);
    }

    @Override
    public ResponseEntity<Optional<Organisation>> getByName(String name) {
        return super.getByName(name);
    }

    @Override
    public ResponseEntity<List<Organisation>> getAll() {
        return super.getAll();
    }
}
