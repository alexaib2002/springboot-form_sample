package org.alexaib.springlearn.springbootform.app.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class User {
        private String idx;
        @NotEmpty(message = "Name cannot be empty")
        @Size(min = 3, max = 8)
        private String name;
        @NotEmpty
        private String surname;
        @NotEmpty
        private String username;
        @NotEmpty
        private String password;
        @NotEmpty
        @Email(message = "Email isn't formatted")
        private String email;

        public User(String idx, String name, String surname, String username, String password, String email) {
                this.idx = idx;
                this.name = name;
                this.surname = surname;
                this.username = username;
                this.password = password;
                this.email = email;
        }

        public String getIdx() {
                return idx;
        }

        public void setIdx(String idx) {
                this.idx = idx;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getSurname() {
                return surname;
        }

        public void setSurname(String surname) {
                this.surname = surname;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }
}
