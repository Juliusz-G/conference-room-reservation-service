package com.sda.conferenceroomreservationservice.model.entity;

import com.sda.conferenceroomreservationservice.constraints.UniqueOrganisationName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Organisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Organisation name can not be blank or empty")
    @Size(min = 2, max = 20, message = "Organisation name must be between 2 - 20 characters long")
    // @UniqueOrganisationName(message = "Organisation name have to be unique") TODO: Throws error
    private String name;

    @NotEmpty(message = "Organisation description can not be blank or empty")
    private String description;
}
