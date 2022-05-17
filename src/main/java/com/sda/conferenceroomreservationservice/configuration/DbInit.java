package com.sda.conferenceroomreservationservice.configuration;

import com.sda.conferenceroomreservationservice.model.entity.Organisation;
import com.sda.conferenceroomreservationservice.repository.OrganisationRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DbInit {

    private final OrganisationRepository organisationRepository;
    private final PasswordEncoder passwordEncoder;


    public DbInit(OrganisationRepository organisationRepository, PasswordEncoder passwordEncoder) {
        this.organisationRepository = organisationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener
    public void init(ContextRefreshedEvent event) {
        organisationRepository.save(new Organisation(null, "admin", "desc", new ArrayList<>(), passwordEncoder.encode("admin")));
    }
}
