package com.sda.conferenceroomreservationservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.conferenceroomreservationservice.service.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class OrganisationControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private OrganisationService organisationService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
}
