package com.futurespace.exercise.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;


public class UserDetailsModel {

    private String id;

    // Maybe include pattern nie/dni
    @NotNull(message="DNI cannot be null!")
    private String DNI;

    @NotNull(message="First name cannot be null!")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only letters")
    private String firstName;

    @NotNull(message="Middle name cannot be null!")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Middle name must contain only letters")
    private String middleName;

    @NotNull(message="Last name cannot be null!")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only letters")
    private String lastName;

    @NotNull(message="Birthdate cannot be null!")
    @Past(message = "Birthdate must be in the past")
    private LocalDate birthdate;

    @NotNull(message="Sex cannot be null!")
    @Pattern(regexp = "^[MF]$", message = "Sex must be M or F")
    private String sex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFullName() {
        return firstName + " " + middleName + " " + lastName;
    }



}

