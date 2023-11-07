package org.alexaib.springlearn.springbootform.app.model;

import jakarta.validation.constraints.*;
import org.alexaib.springlearn.springbootform.app.validator.RegexOID;
import org.alexaib.springlearn.springbootform.app.validator.Required;

import java.util.Date;

public class User {
    private String idx;
    @RegexOID
    private String oid;
    private String name;
    @Required
    private String surname;
    @NotBlank
    @Size(min = 3, max = 8)
    private String username;
    @NotBlank
    private String password;
    @NotEmpty
    @Email(message = "Email isn't formatted")
    private String email;
    @NotNull
    @Min(0)
    @Max(9999)
    private Integer account;
    @NotNull
    @Past
    private Date birthDate;
    @NotNull
    private Country country;

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

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
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

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
