package com.futurespace.exercise.user;

import com.futurespace.exercise.exception.DuplicatePersonaException;
import com.futurespace.exercise.exception.PersonaNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    void getPersonaByDni_ShouldReturnPersona_WhenExists() {
        Persona persona = userService.getPersonaByDni("Y1234567A");
        assertNotNull(persona);
        assertEquals("Adam", persona.getFirstName());
    }

    @Test
    void getPersonaByDni_ShouldThrowException_WhenNotExists() {
        assertThrows(PersonaNotFoundException.class, () -> userService.getPersonaByDni("INVALID_DNI"));
    }

    @Test
    void updatePersona_ShouldUpdatePersona_WhenExists() {
        Persona updatedPersona = new Persona("Y1234567A", "Updated", "User", "Kaawach", LocalDate.of(2002, 9, 14), "M");
        Persona result = userService.updatePersona("Y1234567A", updatedPersona);
        assertEquals("Updated", result.getFirstName());
    }

    @Test
    void updatePersona_ShouldThrowException_WhenNotExists() {
        Persona newPersona = new Persona("INVALID_DNI", "New", "User", "Test", LocalDate.of(2000, 1, 1), "M");
        assertThrows(PersonaNotFoundException.class, () -> userService.updatePersona("INVALID_DNI", newPersona));
    }

    @Test
    void deletePersona_ShouldDeletePersona_WhenExists() {
        Persona deleted = userService.deletePersona("Y1234567A");
        assertEquals("Adam", deleted.getFirstName());
        assertThrows(PersonaNotFoundException.class, () -> userService.getPersonaByDni("Y1234567A"));
    }

    @Test
    void deletePersona_ShouldThrowException_WhenNotExists() {
        assertThrows(PersonaNotFoundException.class, () -> userService.deletePersona("INVALID_DNI"));
    }

    @Test
    void createUser_ShouldCreateUser_WhenDniNotExists() {
        Persona newPersona = new Persona("NEW1234Z", "New", "User", "Test", LocalDate.of(2000, 1, 1), "M");
        Persona result = userService.createUser(newPersona);
        assertNotNull(result);
        assertEquals("New", result.getFirstName());
    }

    @Test
    void createUser_ShouldThrowException_WhenDniExists() {
        // Insert the user first to ensure it's in the map
        Persona existingPersona = new Persona("Y1234567A", "Adam", "Farid", "Kaawach", LocalDate.of(2002, 9, 14), "M");
        userService.createUser(existingPersona); // First call should succeed

        // Now calling createUser again should trigger the exception
        assertThrows(DuplicatePersonaException.class, () -> userService.createUser(existingPersona));
    }
}
