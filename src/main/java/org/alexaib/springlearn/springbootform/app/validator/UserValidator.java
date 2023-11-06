package org.alexaib.springlearn.springbootform.app.validator;

import org.alexaib.springlearn.springbootform.app.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmpty(errors, "name", "NotEmpty.user.name");

        // \\d value repeats the first [0-9] range
        if (!user.getOid().matches("[0-9]{2}[.][\\d]{3}-[A-Z]"))
            errors.rejectValue("oid", "Pattern.user.oid");
    }
}
