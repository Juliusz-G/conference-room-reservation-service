package com.sda.conferenceroomreservationservice.model.entity;

import com.sda.conferenceroomreservationservice.constraints.UniqueConferenceRoomName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ConferenceRoom {

    private static final int MIN_FLOOR_LEVEL = 0;
    private static final int MAX_FLOOR_LEVEL = 10;
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 20;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name can not be blank or empty")
    @Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH,
            message = "Name must be between 2 - 20 characters long")
    @UniqueConferenceRoomName(message = "Name have to be unique")
    private String name;

    @Pattern(regexp = "\\d[.]\\d\\d",
            message = "Identifier have to match format: [0-9].[0-9][0-9]")
    private String identifier;

    @Min(value = MIN_FLOOR_LEVEL, message = "Minimum floor level is 0")
    @Max(value = MAX_FLOOR_LEVEL, message = "Maximum floor level is 10")
    private Integer level;

    private Boolean availability;

    @Positive(message = "Number of standing places is not positive")
    private Integer numberOfStandingPlaces;

    @Positive(message = "Number of sitting places is not positive")
    private Integer numberOfSittingPlaces;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Reservation> reservationList;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Organisation organisation;
}
