package com.sda.conferenceroomreservationservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.conferenceroomreservationservice.model.request.ConferenceRoomRequest;
import com.sda.conferenceroomreservationservice.model.request.OrganisationRequest;
import com.sda.conferenceroomreservationservice.model.request.ReservationRequest;
import com.sda.conferenceroomreservationservice.repository.OrganisationRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class ReservationControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrganisationRepository organisationRepository;

    @BeforeEach
    void cleanDB() {
        organisationRepository.deleteAll();
    }

    @Test
    @Order(1)
    void addReservationFromValidRequestSaveEntityInDatabase() throws Exception {
        final OrganisationRequest organisationRequest = OrganisationRequest.of("Tester", "Test description");

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/organisations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(organisationRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        final ConferenceRoomRequest conferenceRoomRequest = ConferenceRoomRequest.of(1L,"Tester","1.20",1,10,10);

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/conference-room")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(conferenceRoomRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        final ReservationRequest reservationRequest = ReservationRequest.of(LocalDateTime.now(),LocalDateTime.now().plusHours(2L),1L);

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/reservations")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(reservationRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/conference-room"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].name").value("Tester"));
    }

    @Test
    @Order(2)
    void getNonExistingReservationShouldThrowReservationNotFoundException() throws Exception {
        final OrganisationRequest organisationRequest = OrganisationRequest.of("Tester", "Test description");

        final long reservationId = 999L;

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/organisations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(organisationRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        final ConferenceRoomRequest conferenceRoomRequest = ConferenceRoomRequest.of(2L,"Tester","1.20",1,10,10);

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/conference-room")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(conferenceRoomRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        final ReservationRequest reservationRequest = ReservationRequest.of(LocalDateTime.now(),LocalDateTime.now().plusHours(2L),2L);

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reservationRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/reservations/" + reservationId))
                .andDo(print())
                .andDo(print())
                .andExpect(content().string(containsString("Reservation not found!")))
                .andExpect(status().is4xxClientError());
    }


    @Test
    @Order(3)
    void updateReservationShouldUpdateEntity() throws Exception {
        final OrganisationRequest organisationRequest = OrganisationRequest.of("Tester", "Test description");

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/organisations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(organisationRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        final ConferenceRoomRequest conferenceRoomRequest = ConferenceRoomRequest.of(3L,"Tester","1.20",1,10,10);

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/conference-room")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(conferenceRoomRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());


        final ReservationRequest reservationRequest = ReservationRequest.of(LocalDateTime.of(2022,05,20,10,00),LocalDateTime.of(2022,05,20,12,00),3L);
        final ReservationRequest updatedRequest = ReservationRequest.of(LocalDateTime.of(2022,05,20,10,00),LocalDateTime.of(2022,05,20,15,00),3L);

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reservationRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:" + port + "/reservations/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/reservations/3"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("endDateTime").value("2022-05-20T15:00:00"));
    }

    @Test
    @Order(4)
    void deleteReservationShouldDeleteEntity() throws Exception {
        final OrganisationRequest organisationRequest = OrganisationRequest.of("Tester", "Test description");

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/organisations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(organisationRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        final ConferenceRoomRequest conferenceRoomRequest = ConferenceRoomRequest.of(4L,"Tester","1.20",1,10,10);

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/conference-room")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(conferenceRoomRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        final ReservationRequest reservationRequest = ReservationRequest.of(LocalDateTime.now(),LocalDateTime.now().plusHours(2L),4L);

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reservationRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:" + port + "/reservations/4"))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/reservations"))
                .andDo(print())
                .andExpect(content().string(containsString("[]")))
                .andExpect(status().is2xxSuccessful());
    }
}