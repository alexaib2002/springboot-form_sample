package org.alexaib.springlearn.springbootform.app.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RegexOIDValidator implements ConstraintValidator<RegexOID, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // \\d value repeats the first [0-9] range
        return value.matches("[0-9]{2}[.][\\d]{3}-[A-Z]");
    }
}
