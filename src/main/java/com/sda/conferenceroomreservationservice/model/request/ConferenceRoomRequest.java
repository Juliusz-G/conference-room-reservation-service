package com.sda.conferenceroomreservationservice.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.*;

@Getter
@Builder
@RequiredArgsConstructor(staticName = "of")
public class ConferenceRoomRequest {

    private static final int MIN_FLOOR_LEVEL = 0;
    private static final int MAX_FLOOR_LEVEL = 10;
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 20;

    private final Long organisationId;

    @NotBlank(message = "Name can not be blank or empty")
    @Size(
            min = MIN_NAME_LENGTH,
            max = MAX_NAME_LENGTH,
            message = "Name must be between 2 - 20 characters long"
    )
    private final String name;

    @Pattern(
            regexp = "\\d[.]\\d\\d",
            message = "Identifier have to match format: [0-9].[0-9][0-9]"
    )
    private final String identifier;

    @Min(
            value = MIN_FLOOR_LEVEL,
            message = "Minimum floor level is 0"
    )
    @Max(
            value = MAX_FLOOR_LEVEL,
            message = "Maximum floor level is 10"
    )
    private final Integer level;

    @Positive(message = "Number of standing places is not positive")
    private final Integer numberOfStandingPlaces;

    @Positive(message = "Number of sitting places is not positive")
    private final Integer numberOfSittingPlaces;

    private final Boolean availability = true;
}
