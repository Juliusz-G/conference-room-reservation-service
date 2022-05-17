package com.sda.conferenceroomreservationservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "organisation")
@Table(
        name = "organisation",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "organisation_name_unique",
                        columnNames = "organisation_name"
                )
        }
)
public class Organisation {

    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 20;

    @Id
    @SequenceGenerator(
            name = "organisation_sequence",
            sequenceName = "organisation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "organisation_sequence"
    )
    @Column(
            name = "organisation_id",
            updatable = false
    )
    private Long id;

    @NotEmpty(message = "Organisation name can not be blank or empty")
    @Size(
            min = MIN_NAME_LENGTH,
            max = MAX_NAME_LENGTH,
            message = "Organisation name must be between 2 - 20 characters long"
    )
    @Column(
            name = "organisation_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @NotEmpty(message = "Organisation description can not be blank or empty")
    @Column(
            name = "description",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String description;

    @OneToMany
    private List<ConferenceRoom> rooms;
}
