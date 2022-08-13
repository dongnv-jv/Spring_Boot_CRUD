package com.example.spring_boot_crud.validation;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = ValidEmail.class)
@Documented
public @interface ValidEmails {

    String message() default "{validation.validEmail}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}

