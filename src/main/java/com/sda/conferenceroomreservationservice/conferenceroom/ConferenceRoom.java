package com.sda.conferenceroomreservationservice.conferenceroom;

import com.sda.conferenceroomreservationservice.organisation.Organisation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ConferenceRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 55)
    private String name;

    @Size(min = 2, max = 55)
    private String identifier;

    @Min(value = 0)
    @Max(value = 10)
    private Integer level;

    private Boolean availability;

    @Positive
    private Integer numberOfPlaces;

    @ManyToOne
    private Organisation organisation;
}