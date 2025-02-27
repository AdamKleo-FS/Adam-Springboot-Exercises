package com.futurespace.exercise.user;

import java.time.LocalDate;

public class Persona {
    private String DNI;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthdate;
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
