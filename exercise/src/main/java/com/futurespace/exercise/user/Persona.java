package com.futurespace.exercise.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public class Persona {
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
    // Constructor
    public Persona(String DNI, String firstName, String middleName, String lastName, LocalDate birthdate, String sex) {
        this.DNI = DNI;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.sex = sex;
    }

    // Getters and Setters
    public String getDNI() { return DNI; }
    public void setDNI(String DNI) { this.DNI = DNI; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getMiddleName() { return middleName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public LocalDate getBirthdate() { return birthdate; }
    public void setBirthdate(LocalDate birthdate) { this.birthdate = birthdate; }

    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }

    public String getFullName() {
        return firstName + " " + middleName + " " + lastName;
    }
}
