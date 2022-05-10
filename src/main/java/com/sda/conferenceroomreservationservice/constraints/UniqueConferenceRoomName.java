package com.sda.conferenceroomreservationservice.constraints;

import com.sda.conferenceroomreservationservice.constraints.validator.UniqueConferenceRoomNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueConferenceRoomNameValidator.class)
@Documented
public @interface UniqueConferenceRoomName {
    String message() default "Name is not unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
