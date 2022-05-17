package com.sda.conferenceroomreservationservice.security;

import com.sda.conferenceroomreservationservice.repository.OrganisationRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final OrganisationRepository organisationRepository;

    public CustomUserDetailsService(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String name) throws UsernameNotFoundException {
        return organisationRepository.findByName(name)
                .map(UserDetailsAdapter::new)
                .orElseThrow(() -> new UsernameNotFoundException("Organisation with that name not found"));
    }
}
