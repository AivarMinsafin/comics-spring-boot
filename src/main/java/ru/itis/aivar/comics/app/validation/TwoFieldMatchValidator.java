package ru.itis.aivar.comics.app.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TwoFieldMatchValidator implements ConstraintValidator<TwoFieldsMatch, Object> {

    private String password;
    private String passwordVerification;

    @Override
    public void initialize(TwoFieldsMatch constraintAnnotation) {
        this.password = constraintAnnotation.first();
        this.passwordVerification = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Object password = new BeanWrapperImpl(o).getPropertyValue(this.password);
        Object passwordVerification = new BeanWrapperImpl(o).getPropertyValue(this.passwordVerification);

        return password!=null && password.equals(passwordVerification);
    }
}
