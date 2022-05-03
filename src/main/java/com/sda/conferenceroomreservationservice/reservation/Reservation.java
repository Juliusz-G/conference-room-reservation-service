package com.sda.conferenceroomreservationservice.reservation;

import com.sda.conferenceroomreservationservice.conferenceroom.ConferenceRoom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime start;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime end;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private ConferenceRoom conferenceRoom;
}
