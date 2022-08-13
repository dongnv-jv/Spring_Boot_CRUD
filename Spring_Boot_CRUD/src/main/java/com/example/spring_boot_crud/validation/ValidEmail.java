package com.example.spring_boot_crud.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidEmail implements ConstraintValidator<ValidEmails,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s.matches("(.*)@gmail(.*)")){
           return true;
        } else
            return false;
    }
}
