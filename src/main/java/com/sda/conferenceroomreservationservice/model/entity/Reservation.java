package com.sda.conferenceroomreservationservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
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
