package com.futurespace.exercise.user;


public interface UserServiceInterface {
    Persona getPersonaByDni(String dni);
    Persona updatePersona(String dni, Persona updatedPersona);
    Persona deletePersona(String dni);
    Persona createUser(Persona persona);
}
