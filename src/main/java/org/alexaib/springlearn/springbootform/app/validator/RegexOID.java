package org.alexaib.springlearn.springbootform.app.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RegexOIDValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD})
public @interface RegexOID {

    String message() default "Invalid OID";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
