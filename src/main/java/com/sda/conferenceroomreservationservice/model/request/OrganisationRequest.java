package com.sda.conferenceroomreservationservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Builder
@RequiredArgsConstructor(staticName = "of")
public class OrganisationRequest {

    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 20;

    @NotEmpty(message = "Organisation name can not be blank or empty")
    @Size(
            min = MIN_NAME_LENGTH,
            max = MAX_NAME_LENGTH,
            message = "Organisation name must be between 2 - 20 characters long"
    )
    private final String name;
    private final String description;
}
