package org.alexaib.springlearn.springbootform.app.model;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public record User(
        @Value("")
        @NotEmpty
        String username,
        @Value("")
        @NotEmpty
        String password,
        @Value("")
        @NotEmpty
        String email
) {
}
