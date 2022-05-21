package com.sda.conferenceroomreservationservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.conferenceroomreservationservice.model.request.ConferenceRoomRequest;
import com.sda.conferenceroomreservationservice.model.request.OrganisationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ConferenceRoomControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addConferenceRoomFromValidRequestSaveEntityInDatabase() throws Exception {
        final OrganisationRequest organisationRequest = OrganisationRequest.of("Tester", "Test description");

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/organisations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(organisationRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        final ConferenceRoomRequest conferenceRoomRequest = ConferenceRoomRequest.of(1L,"Tester","1.20",1,10,10,true);

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/conference-room")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(conferenceRoomRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/conference-room"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].name").value("Tester"));
    }

    @Test
    void getNonExistingConferenceRoomShouldThrowConferenceRoomNotFoundException() throws Exception {
        final OrganisationRequest organisationRequest = OrganisationRequest.of("Tester", "Test description");

        final long conferenceRoomId = 999L;

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/organisations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(organisationRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        final ConferenceRoomRequest conferenceRoomRequest = ConferenceRoomRequest.of(1L,"Tester","1.20",1,10,10,true);

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/conference-room")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(conferenceRoomRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/conference-room/" + conferenceRoomId))
                .andDo(print())
                .andDo(print())
                .andExpect(content().string(containsString("Conference room not found!")))
                .andExpect(status().is4xxClientError());
    }


    @Test
    void updateConferenceRoomShouldUpdateEntity() throws Exception {
        final OrganisationRequest organisationRequest = OrganisationRequest.of("Tester", "Test description");

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/organisations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(organisationRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        final ConferenceRoomRequest conferenceRoomRequest = ConferenceRoomRequest.of(1L,"Tester","1.20",1,10,10,true);
        final ConferenceRoomRequest updatedRequest = ConferenceRoomRequest.of(1L,"Tester","1.20",5,10,10,true);

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/conference-room")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(conferenceRoomRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:" + port + "/conference-room/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/conference-room/1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("level").value(5))
                .andExpect(jsonPath("name").value("Tester"));
    }
}