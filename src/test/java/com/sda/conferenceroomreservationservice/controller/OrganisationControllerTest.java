package com.sda.conferenceroomreservationservice.controller;

import com.sda.conferenceroomreservationservice.service.OrganisationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;


@WebMvcTest(OrganisationController.class)
class OrganisationControllerTest {

    @MockBean
    private OrganisationService organisationService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllOrganisationsListReturnEmptyListWhenNoOrganisationAdded() throws Exception {
        Mockito.when((organisationService.getAll()))
                .thenReturn(List.of());

        mockMvc.perform((MockMvcRequestBuilders.get("/organisations")))
                .andDo(print())
                .andExpect(content().string(containsString("[]")))
                .andExpect(status().is(200));
    }

}