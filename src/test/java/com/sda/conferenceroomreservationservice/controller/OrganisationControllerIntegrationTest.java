package com.sda.conferenceroomreservationservice.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.conferenceroomreservationservice.model.request.OrganisationRequest;
import com.sda.conferenceroomreservationservice.repository.OrganisationRepository;
import org.junit.jupiter.api.*;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrganisationControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    OrganisationRepository organisationRepository;

    @BeforeEach
    void cleanDB() {
        organisationRepository.deleteAll();
    }

    @Test
    @Order(1)
    void addOrganisationFromValidRequestSaveEntityInDatabase() throws Exception {
        final OrganisationRequest organisationRequest = OrganisationRequest.of("Tester", "Test description");

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/organisations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(organisationRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/organisations"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].name").value("Tester"));
    }


    @Test
    @Order(2)
    void getOrganisationByIdShouldReturnOrganisationNotFoundException() throws Exception {
        final OrganisationRequest organisationRequest = OrganisationRequest.of("Tester", "Test description");
        final long organisationId = 999L;

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/organisations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(organisationRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/organisations/" + organisationId))
                .andDo(print())
                .andExpect(content().string(containsString("Organisation not found!")))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @Order(3)
    void updateOrganisationShouldUpdateEntity() throws Exception {
        final OrganisationRequest organisationRequest = OrganisationRequest.of("Tester", "Test description");
        final OrganisationRequest updatedRequest = OrganisationRequest.of("Tester", "Updated description");

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/organisations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(organisationRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:" + port + "/organisations/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/organisations/3"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("description").value("Updated description"))
                .andExpect(jsonPath("name").value("Tester"));
    }

    @Test
    @Order(4)
    void deleteOrganisationShouldDeleteEntity() throws Exception {
        final OrganisationRequest organisationRequest = OrganisationRequest.of("Tester", "Test description");
        final long organisationId = 4L;

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/organisations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(organisationRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());


        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:" + port + "/organisations/" + organisationId))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/organisations"))
                .andDo(print())
                .andExpect(content().string(containsString("[]")))
                .andExpect(status().is2xxSuccessful());
    }
}
