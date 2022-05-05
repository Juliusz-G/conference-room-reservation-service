package com.sda.conferenceroomreservationservice.organisation.validation;

import com.sda.conferenceroomreservationservice.organisation.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, String> {

    private final OrganisationRepository repository;

    @Autowired
    public UniqueNameValidator(OrganisationRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(String nameField, ConstraintValidatorContext cxt) {
        return repository.findByName(nameField).isEmpty();
    }

}