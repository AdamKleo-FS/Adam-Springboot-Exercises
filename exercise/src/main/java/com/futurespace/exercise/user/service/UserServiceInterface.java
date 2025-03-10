package com.futurespace.exercise.user.service;


import com.futurespace.exercise.user.model.Persona;

public interface UserServiceInterface {
    Persona getPersonaByDni(String dni);
    Persona updatePersona(String dni, Persona updatedPersona);
    Persona deletePersona(String dni);
    Persona createUser(Persona persona);
}
