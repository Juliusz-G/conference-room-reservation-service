package com.sda.conferenceroomreservationservice.organisation;

import com.sda.conferenceroomreservationservice.organisation.validation.UniqueName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Organisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Organisation name can not be blank or empty")
    @Size(min = 2, max = 20, message = "Organisation name must be between 2 - 20 characters long")
    @UniqueName(message = "Organisation name have to be unique")
    private String name;

    @NotEmpty(message = "Organisation description can not be blank or empty")
    private String description;

}
