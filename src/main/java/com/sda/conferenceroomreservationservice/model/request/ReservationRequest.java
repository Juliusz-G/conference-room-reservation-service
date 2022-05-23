package com.sda.conferenceroomreservationservice.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor(staticName = "of")
public class ReservationRequest {

    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 20;

    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private final Long conferenceRoomId;
//    private final String conferenceRoomName;
}
