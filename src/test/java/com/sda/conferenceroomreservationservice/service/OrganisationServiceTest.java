package com.sda.conferenceroomreservationservice.service;

import com.sda.conferenceroomreservationservice.mapper.OrganisationMapper;
import com.sda.conferenceroomreservationservice.model.entity.ConferenceRoom;
import com.sda.conferenceroomreservationservice.model.entity.Organisation;
import com.sda.conferenceroomreservationservice.model.request.OrganisationRequest;
import com.sda.conferenceroomreservationservice.model.response.OrganisationResponse;
import com.sda.conferenceroomreservationservice.repository.OrganisationRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class OrganisationServiceTest {

    @Mock
    private OrganisationRepository organisationRepository;

    @Mock
    private OrganisationMapper organisationMapper;

    @Mock
    private Organisation organisationEntity;

    @InjectMocks
    private OrganisationService organisationService;

    @Test
    void getAllOrganisationsListWhenDatabaseIsNotEmpty() {
        final Organisation organisation = new Organisation();
        organisation.setName("Tester");

        Mockito.when(organisationRepository.findAll())
                .thenReturn(List.of(organisation, new Organisation()));

        final List<OrganisationResponse> resultOrganisationsList = organisationService.getAll();

        Assertions.assertThat(resultOrganisationsList).hasSize(2);
        Assertions.assertThat(resultOrganisationsList.get(0).getName()).isEqualTo("Tester");
    }

    @Test
    void getAllOrganisationsListReturnEmptyListWhenNoOrganisationAdded() {
        Mockito.when(organisationRepository.findAll())
                .thenReturn(Collections.emptyList());

        final List<OrganisationResponse> resultOrganisationsList = organisationService.getAll();

        Assertions.assertThat(resultOrganisationsList).isEmpty();
    }

//    @Test
//    void addOrganisationWhenNotAlreadyExistingInDatabase() {
//        final Long organisationId = 0L;
//        final String organisationName = "Tester";
//        final String organisationDescription = "Test description";
//        final List<String> conferenceRooms = List.of("Room 1");
//        final List<ConferenceRoom> conferenceRoomList = List.of(new ConferenceRoom());
//
//        final OrganisationRequest organisationRequest = new OrganisationRequest(
//                organisationName,
//                organisationDescription
//        );
//
//        final OrganisationResponse organisationResponse = new OrganisationResponse(
//                organisationId,
//                organisationName,
//                organisationDescription,
//                conferenceRooms
//        );
//
//        final Organisation organisation = new Organisation(
//                organisationId,
//                organisationName,
//                organisationDescription,
//                conferenceRoomList
//        );
//
////        try (MockedStatic<OrganisationMapper> mapper = Mockito.mockStatic(OrganisationMapper.class)) {
////            mapper.when(() -> OrganisationMapper.map(organisation))
////                    .thenReturn(organisationResponse);
////            Assertions.assertThat(organisationMapper.map(organisation)).isEqualTo(organisationResponse);
////        }
//
//        Mockito.when(organisationEntity.getRooms())
//                .thenReturn(conferenceRoomList);
//
//
//        OrganisationResponse result = organisationService.create(organisationRequest);
//
//        Assertions.assertThat(result).isEqualTo(organisationResponse);
//    }
}