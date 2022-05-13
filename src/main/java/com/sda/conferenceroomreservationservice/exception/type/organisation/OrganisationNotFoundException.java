package com.sda.conferenceroomreservationservice.exception.type.organisation;

import java.util.NoSuchElementException;

public class OrganisationNotFoundException extends NoSuchElementException {

    public OrganisationNotFoundException() {
        super("Organisation not found!");
    }
}
