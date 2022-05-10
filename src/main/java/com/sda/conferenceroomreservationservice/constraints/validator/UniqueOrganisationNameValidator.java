package com.sda.conferenceroomreservationservice.constraints.validator;

import com.sda.conferenceroomreservationservice.constraints.UniqueOrganisationName;
import com.sda.conferenceroomreservationservice.model.entity.Organisation;
import com.sda.conferenceroomreservationservice.repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueOrganisationNameValidator implements ConstraintValidator<UniqueOrganisationName, String> {

    private final OrganisationRepository organisationRepository;

    @Autowired
    public UniqueOrganisationNameValidator(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }

    @Override
    public boolean isValid(String nameField, ConstraintValidatorContext cxt) {
        return organisationRepository.findAll().stream().map(Organisation::getName).distinct().count() ==
                organisationRepository.findAll().size();
    }
}