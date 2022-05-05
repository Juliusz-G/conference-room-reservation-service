package com.sda.conferenceroomreservationservice.conferenceroom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ConferenceRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
}