package com.sda.conferenceroomreservationservice.reservation;

import com.sda.conferenceroomreservationservice.conferenceroom.ConferenceRoom;
import com.sda.conferenceroomreservationservice.organisation.Organisation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Timestamp start;

    private Timestamp end;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private ConferenceRoom conferenceRoom;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Organisation organisation;
}
