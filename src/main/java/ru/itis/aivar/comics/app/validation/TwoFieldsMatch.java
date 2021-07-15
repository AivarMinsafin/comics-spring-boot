package ru.itis.aivar.comics.app.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TwoFieldMatchValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TwoFieldsMatch {

    String message() default "fields must be equal";

    String first();
    String second();

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        TwoFieldsMatch[] value();
    }

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
