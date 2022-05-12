package com.sda.conferenceroomreservationservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

// enables builder pattern
@Builder
// generates toString, equals, hashCode, getters,
// setters and required args constructor
@Data
// generates no args constructor
@NoArgsConstructor
// generates all args constructor
@AllArgsConstructor
// tells JPA that it's entity
// (POJO representing data that can be persisted to the DB)
@Entity
public class ConferenceRoom {

    private static final Integer MIN_FLOOR_LEVEL = 0;
    private static final Integer MAX_FLOOR_LEVEL = 10;
    private static final Integer MIN_NAME_LENGTH = 2;
    private static final Integer MAX_NAME_LENGTH = 20;

    // marks a field in a model class as the primary key
    @Id
    // It relies on an auto-incremented database column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // must be not null, and the trimmed length must be greater than zero
    @NotBlank(message = "Name can not be blank or empty")
    // validate the size of a field,
    // makes the bean independent of JPA and its vendors such as Hibernate
    @Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH,
            message = "Name must be between 2 - 20 characters long")
    // TODO: Custom annotation not working - throws error
    // @UniqueConferenceRoomName(message = "Name have to be unique")
    private String name;

    // field is only valid when it matches a certain regular expression
    @Pattern(regexp = "\\d[.]\\d\\d",
            message = "Identifier have to match format: [0-9].[0-9][0-9]")
    private String identifier;

    // validates that the annotated property has a value
    // no smaller than the value attribute
    @Min(value = MIN_FLOOR_LEVEL, message = "Minimum floor level is 0")
    // validates that the annotated property has a value
    // no larger than the value attribute
    @Max(value = MAX_FLOOR_LEVEL, message = "Maximum floor level is 10")
    private Integer level;

    private Boolean availability;

    // validate that the value is strictly positive
    @Positive(message = "Number of standing places is not positive")
    private Integer numberOfStandingPlaces;

    // validate that the value is strictly positive
    @Positive(message = "Number of sitting places is not positive")
    private Integer numberOfSittingPlaces;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Reservation> reservationList;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Organisation organisation;
}
