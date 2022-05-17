package com.sda.conferenceroomreservationservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "reservation")
@Table(name = "reservation")
public class Reservation {

    @Id
    @SequenceGenerator(
            name = "reservation_sequence",
            sequenceName = "reservation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "reservation_sequence"
    )
    @Column(
            name = "reservation_id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "start_date_time",
            nullable = false,
            columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime startDateTime;

    @Column(
            name = "end_date_time",
            nullable = false,
            columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime endDateTime;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private ConferenceRoom conferenceRoom;

}
