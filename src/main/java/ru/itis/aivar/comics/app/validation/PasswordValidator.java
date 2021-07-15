package ru.itis.aivar.comics.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.length()>7
                && s.matches(".*[A-Z].*")
                && s.matches(".*[a-z].*")
                && s.matches(".*[0-9].*");
    }
}
