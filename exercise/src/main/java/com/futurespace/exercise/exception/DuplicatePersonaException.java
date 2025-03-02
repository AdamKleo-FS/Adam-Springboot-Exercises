package com.futurespace.exercise.exception;

public class DuplicatePersonaException extends RuntimeException {
    public DuplicatePersonaException(String dni) {
        super("Persona with DNI " + dni + " already exists");
    }
}
