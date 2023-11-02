package org.alexaib.springlearn.springbootform.app.model;

public record User(
        String username,
        String password,
        String email
) {
}
