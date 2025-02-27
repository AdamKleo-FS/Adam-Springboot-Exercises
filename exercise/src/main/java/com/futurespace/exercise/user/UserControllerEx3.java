package com.futurespace.exercise.user;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users3")
public class UserControllerEx3 {

    private final UserServiceInterface userService;

    public UserControllerEx3(UserServiceInterface userService) {
        this.userService = userService;
    }

    /*******************************
     ********* GET (Step 1) *********
     *******************************/
    @GetMapping("/{dni}")
    public ResponseEntity<Persona> getPersonaByDni(@PathVariable String dni) {
        Persona persona = userService.getPersonaByDni(dni);
        if (persona == null) {
            // Devolver status NOT FOUND si no se encuentra
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

    /*******************************
     ********* PUT (Step 2) *********
     *******************************/
    @PutMapping("/{dni}")
    public ResponseEntity<Persona> updatePersonaByDni(
            @PathVariable String dni,
            @Valid @RequestBody Persona updatedPersona) {
        // UserDetailsModel para que podamos validar la entrada.
        Persona persona = userService.updatePersona(dni, updatedPersona);
        if (persona != null) {
            return ResponseEntity.ok(persona);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    /********************************
     ****** DELETE (Optional) *******
     ********************************/
    @DeleteMapping("/{dni}")
    public ResponseEntity<Persona> deletePersonaByDni(@PathVariable String dni) {
        Persona persona = userService.deletePersona(dni);
        if (persona != null) {
            return ResponseEntity.ok(persona);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
