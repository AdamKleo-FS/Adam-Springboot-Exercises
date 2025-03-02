package com.futurespace.exercise.exception;

public class PersonaNotFoundException extends RuntimeException {
    public PersonaNotFoundException(String dni) {
        super("Persona with DNI " + dni + " not found");
    }
}
