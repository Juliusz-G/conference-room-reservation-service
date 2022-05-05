package com.sda.conferenceroomreservationservice.organisation.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueNameValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueName {
    String message() default "Field is not unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}