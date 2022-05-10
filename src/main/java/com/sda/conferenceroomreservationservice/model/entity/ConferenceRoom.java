package com.sda.conferenceroomreservationservice.model.entity;

import com.sda.conferenceroomreservationservice.constraints.UniqueConferenceRoomName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Builder
@Data // generates toString, equals and hashCode, getters, setters and required args constructor
@NoArgsConstructor // generates no args constructor
@AllArgsConstructor // generates all args constructor
@Entity // tells JPA that it's entity (POJO representing data that can be persisted to the DB)
public class ConferenceRoom {

    @Id // marks a field in a model class as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // It relies on an auto-incremented database column
    private Long id;

    // must be not null, and the trimmed length must be greater than zero
    @NotBlank(message = "Conference room name can not be blank or empty")
    // validate the size of a field, makes the bean independent of JPA and its vendors such as Hibernate
    @Size(min = 2, max = 20, message = "Conference room name must be between 2 - 20 characters long")
    // @UniqueConferenceRoomName(message = "Conference room name have to be unique") TODO: Throws error
    private String name;

    // field is only valid when it matches a certain regular expression
    @Pattern(regexp = "\\d[.]\\d\\d",
            message = "Conference room identifier have to match following format: [0-9].[0-9][0-9]")
    private String identifier;

    @Min(value = 0) // validates that the annotated property has a value no smaller than the value attribute
    @Max(value = 10) // validates that the annotated property has a value no larger than the value attribute
    private Integer level;

    private Boolean availability;

    // validate that the value is strictly positive
    @Positive(message = "Conference room number of places have to a positive integer")
    private Integer numberOfPlaces;
}