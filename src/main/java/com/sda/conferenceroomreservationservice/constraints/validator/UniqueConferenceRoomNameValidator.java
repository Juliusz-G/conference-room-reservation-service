package com.sda.conferenceroomreservationservice.constraints.validator;

import com.sda.conferenceroomreservationservice.constraints.UniqueConferenceRoomName;
import com.sda.conferenceroomreservationservice.repository.ConferenceRoomRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueConferenceRoomNameValidator implements ConstraintValidator<UniqueConferenceRoomName, String> {

    private final ConferenceRoomRepository conferenceRoomRepository;

    @Override
    public boolean isValid(String nameField, ConstraintValidatorContext cxt) {
        return conferenceRoomRepository.findAll()
                .stream()
                .anyMatch(conferenceRoom -> conferenceRoom.getName().equals(nameField));
    }
}