package com.sda.conferenceroomreservationservice.exception.type.organisation;

public class OrganisationAlreadyExists extends RuntimeException{
    public OrganisationAlreadyExists() {
        super("Organisation with that name already exists!");
    }
}
