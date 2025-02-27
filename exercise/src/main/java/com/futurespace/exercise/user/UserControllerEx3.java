package com.futurespace.exercise.user;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users3")
public class UserControllerEx3 {

    private List<Persona> personas;

    // Crear 10 personas cuando iniciamos la clase
    public UserControllerEx3() {
        personas = new ArrayList<>();
        personas.add(new Persona("Y1234567A", "Adam", "Farid", "Kaawach", LocalDate.of(2002, 9, 14), "M"));
        personas.add(new Persona("Y8229158B", "Mohammad", "Marwan", "Hashem", LocalDate.of(2003, 8, 2), "M"));
        personas.add(new Persona("X3344567C", "Sarah", "Isabel", "Fernández", LocalDate.of(2001, 6, 21), "F"));
        personas.add(new Persona("X2256789D", "Omar", "Tariq", "Hassan", LocalDate.of(2000, 12, 5), "M"));
        personas.add(new Persona("X9876543E", "Leila", "Amira", "Mahmoud", LocalDate.of(1999, 3, 15), "F"));
        personas.add(new Persona("X1239876F", "Daniel", "José", "Martínez", LocalDate.of(2004, 5, 19), "M"));
        personas.add(new Persona("X6667788G", "Sofía", "Lucía", "Gómez", LocalDate.of(1998, 7, 9), "F"));
        personas.add(new Persona("X7654321H", "Carlos", "Antonio", "Ruiz", LocalDate.of(2005, 2, 11), "M"));
        personas.add(new Persona("X9988776I", "Amina", "Zahra", "Benali", LocalDate.of(2002, 10, 25), "F"));
        personas.add(new Persona("X5678901J", "Miguel", "Andrés", "Navarro", LocalDate.of(2003, 4, 30), "M"));
    }

    /*******************************
     ********* GET (Step 1) *********
     *******************************/
    @GetMapping("/{dni}")
    public ResponseEntity<Persona> getPersonaByDni(@PathVariable String dni) {
        // Buscar persona por DNI
        for (Persona persona : personas) {
            if (persona.getDNI().equalsIgnoreCase(dni)) {
                // Devolver el usuario si se encuentra
                return ResponseEntity.ok(persona);
            }
        }
        // Devolver status NOT FOUND si no se encuentra
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /*******************************
     ********* PUT (Step 2) *********
     *******************************/
    @PutMapping("/{dni}")
    public ResponseEntity<Persona> updatePersonaByDni(
            @PathVariable String dni,
            @Valid @RequestBody UserDetailsModel updatedUser) {
        // UserDetailsModel para que podamos validar la entrada.
        for (int i = 0; i < personas.size(); i++) {

            // Buscar persona por DNI
            if (personas.get(i).getDNI().equalsIgnoreCase(dni)) {
                // Crear la nueva persona
                Persona updatedPersona = new Persona(
                        updatedUser.getDNI(),
                        updatedUser.getFirstName(),
                        updatedUser.getMiddleName(),
                        updatedUser.getLastName(),
                        updatedUser.getBirthdate(),
                        updatedUser.getSex()
                );

                // Actualizar la persona
                personas.set(i, updatedPersona);
                return ResponseEntity.ok(updatedPersona);
            }
        }
        // Persona no se encuentra
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    /********************************
     ****** DELETE (Optional) *******
     ********************************/
    @DeleteMapping("/{dni}")
    public ResponseEntity<Persona> deletePersonaByDni(@PathVariable String dni) {
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getDNI().equalsIgnoreCase(dni)) {
                Persona persona = personas.get(i);
                personas.remove(i);
                return ResponseEntity.ok(persona);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
