package com.futurespace.exercise.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class Persona {

    @NotNull(message="El DNI no puede ser nulo")
    @Pattern(
            regexp = "^[0-9]{8}[A-Z]$|^[XYZ][0-9]{7}[A-Z]$",
            message = "El DNI/NIE debe tener un formato válido (Ejemplo: 12345678A o X1234567B)"
    )
    private String DNI;

    @NotNull(message="El nombre no puede ser nulo")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "El nombre solo puede contener letras")
    @Size(min = 3, max = 15, message = "El nombre debe tener entre 3 y 15 caracteres")
    private String firstName;

    @NotNull(message="El segundo nombre no puede ser nulo")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "El segundo nombre solo puede contener letras")
    @Size(min = 3, max = 15, message = "El segundo nombre debe tener entre 3 y 15 caracteres")
    private String middleName;

    @NotNull(message="El apellido no puede ser nulo")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "El apellido solo puede contener letras")
    @Size(min = 3, max = 15, message = "El apellido debe tener entre 3 y 15 caracteres")
    private String lastName;

    @NotNull(message="La fecha de nacimiento no puede ser nula")
    @Past(message = "La fecha de nacimiento debe estar en el pasado")
    private LocalDate birthdate;

    @NotNull(message="El sexo no puede ser nulo")
    @Pattern(regexp = "^[MF]$", message = "El sexo debe ser M o F")
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
