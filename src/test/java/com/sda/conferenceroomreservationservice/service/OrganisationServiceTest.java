package com.sda.conferenceroomreservationservice.service;

import com.sda.conferenceroomreservationservice.model.dto.OrganisationDto;
import com.sda.conferenceroomreservationservice.model.entity.ConferenceRoom;
import com.sda.conferenceroomreservationservice.model.entity.Organisation;
import com.sda.conferenceroomreservationservice.repository.OrganisationRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrganisationServiceTest {

    @Mock
    private OrganisationRepository organisationRepository;

    @InjectMocks
    private OrganisationService organisationService;

    @Test
    void getAllOrganisationsListWhenDatabaseIsNotEmpty() {
        final Organisation organisation = new Organisation();
        organisation.setName("Tester");

        Mockito.when(organisationRepository.findAll())
                .thenReturn(List.of(organisation, new Organisation()));

        final List<OrganisationDto> resultOrganisationsList = organisationService.getAll();

        Assertions.assertThat(resultOrganisationsList).hasSize(2);
        Assertions.assertThat(resultOrganisationsList.get(0).getName()).isEqualTo("Tester");
    }

    @Test
    void getAllOrganisationsListReturnEmptyListWhenNoOrganisationAdded() {
        Mockito.when(organisationRepository.findAll())
                .thenReturn(Collections.emptyList());

        final List<OrganisationDto> resultOrganisationsList = organisationService.getAll();

        Assertions.assertThat(resultOrganisationsList).isEmpty();
    }

    @Test
    void addOrganisationWhenNotAlreadyExistingInDatabase() {
        final Long organisationId = 0L;
        final String organisationName = "Tester";
        final String organisationDescription = "Test description";
        final ConferenceRoom conferenceRoom = new ConferenceRoom();
        final List<ConferenceRoom> organisationRooms = List.of(conferenceRoom);

        final Organisation organisation = new Organisation(
                organisationId,
                organisationName,
                organisationDescription,
                organisationRooms
        );
        OrganisationDto result = organisationService.create(organisation);

        Mockito.when(organisationRepository.findByName(Mockito.anyString()))
                .thenReturn(null);
        Mockito.when(organisationRepository.save(organisation))
                .thenReturn(organisation);
        Mockito.when(organisationService.create(organisation))
                .thenReturn(result);

        Mockito.verify(organisationRepository).save(organisation);
        Mockito.verify(organisationService).create(organisation);
    }
}