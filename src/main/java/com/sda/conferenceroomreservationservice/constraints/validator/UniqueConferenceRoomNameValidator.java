package com.sda.conferenceroomreservationservice.constraints.validator;

import com.sda.conferenceroomreservationservice.constraints.UniqueConferenceRoomName;
import com.sda.conferenceroomreservationservice.repository.ConferenceRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueConferenceRoomNameValidator implements ConstraintValidator<UniqueConferenceRoomName, String> {

    private final ConferenceRoomRepository conferenceRoomRepository;

    @Autowired
    public UniqueConferenceRoomNameValidator(ConferenceRoomRepository conferenceRoomRepository) {
        this.conferenceRoomRepository = conferenceRoomRepository;
    }

    @Override
    public boolean isValid(String nameField, ConstraintValidatorContext cxt) {
        return conferenceRoomRepository.findByName(nameField).isEmpty();
    }
}