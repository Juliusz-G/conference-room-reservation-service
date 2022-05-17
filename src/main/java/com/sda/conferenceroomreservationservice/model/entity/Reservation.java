package com.sda.conferenceroomreservationservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "reservation")
@Table
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotEmpty(message = "Date and time of start reservation can not be blank or empty.")
    private LocalDateTime startDateTime;

//    @NotEmpty(message = "Date and time of start reservation can not be blank or empty.")
    private LocalDateTime endDateTime;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private ConferenceRoom conferenceRoom;

}
