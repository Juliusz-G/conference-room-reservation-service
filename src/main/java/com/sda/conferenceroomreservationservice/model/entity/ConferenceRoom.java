package com.sda.conferenceroomreservationservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "conference_room")
@Table(
        name = "conference_room",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "conference_room_name_unique",
                        columnNames = "conference_room_name"
                ),
                @UniqueConstraint(
                        name = "conference_room_identifier_unique",
                        columnNames = "identifier"
                )
        }
)
public class ConferenceRoom {

    private static final int MIN_FLOOR_LEVEL = 0;
    private static final int MAX_FLOOR_LEVEL = 10;
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 20;

    @Id
    @SequenceGenerator(
            name = "conference_room_sequence",
            sequenceName = "conference_room_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "conference_room_sequence"
    )
    @Column(
            name = "conference_room_id",
            updatable = false
    )
    private Long id;

    @NotBlank(message = "Name can not be blank or empty")
    @Size(
            min = MIN_NAME_LENGTH,
            max = MAX_NAME_LENGTH,
            message = "Name must be between 2 - 20 characters long"
    )
    @Column(
            name = "conference_room_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @Pattern(
            regexp = "\\d[.]\\d\\d",
            message = "Identifier have to match format: [0-9].[0-9][0-9]"
    )
    @Column(
            name = "identifier",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String identifier;

    @Min(
            value = MIN_FLOOR_LEVEL,
            message = "Minimum floor level is 0"
    )
    @Max(
            value = MAX_FLOOR_LEVEL,
            message = "Maximum floor level is 10"
    )
    @Column(
            name = "level",
            nullable = false,
            columnDefinition = "INTEGER"
    )
    private Integer level;

    @Column(
            name = "availability",
            nullable = false,
            columnDefinition = "BOOLEAN"
    )
    private Boolean availability;

    @Positive(message = "Number of standing places is not positive")
    @Column(
            name = "number_of_standing_places",
            nullable = false,
            columnDefinition = "INTEGER"
    )
    private Integer numberOfStandingPlaces;

    @Positive(message = "Number of sitting places is not positive")
    @Column(
            name = "number_of_sitting_places",
            nullable = false,
            columnDefinition = "INTEGER"
    )
    private Integer numberOfSittingPlaces;

    @OneToMany(
            mappedBy = "conferenceRoom",
            cascade = CascadeType.REMOVE
    )
    private List<Reservation> reservationList;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id")
    private Organisation organisation;
}
