package com.sda.conferenceroomreservationservice.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
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
public class OrganisationControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addOrganisationFromValidRequestSaveEntityInDatabase() throws Exception {
        final String organisationName = "Tester";
        final String organisationDescription = "Test description";
        final OrganisationRequest organisationRequest = new OrganisationRequest(
                organisationName,
                organisationDescription
        );

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
    void getOrganisationByIdShouldReturnOrganisationNotFoundException() throws Exception {
        final String organisationName = "Tester";
        final String organisationDescription = "Test description";
        final OrganisationRequest organisationRequest = new OrganisationRequest(
                organisationName,
                organisationDescription
        );
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
    void updateOrganisationShouldUpdateEntity() throws Exception {
        final String organisationName = "Tester";
        final String organisationDescription = "Test description";
        final String updateDescription = "Updated description";
        final OrganisationRequest organisationRequest = new OrganisationRequest(
                organisationName,
                organisationDescription
        );
        final OrganisationRequest updatedRequest = new OrganisationRequest(
                organisationName,
                updateDescription
        );

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/organisations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(organisationRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:" + port + "/organisations/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/organisations/1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("description").value("Updated description"))
                .andExpect(jsonPath("name").value("Tester"));
    }

    @Test
    void deleteOrganisationShouldDeleteEntity() throws Exception {
        final String organisationName = "Tester";
        final String organisationDescription = "Test description";
        final long organisationId = 1L;
        final OrganisationRequest organisationRequest = new OrganisationRequest(
                organisationName,
                organisationDescription
        );

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
