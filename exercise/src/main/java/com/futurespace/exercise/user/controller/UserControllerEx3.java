package com.futurespace.exercise.user.controller;

import com.futurespace.exercise.user.model.Persona;
import com.futurespace.exercise.user.service.UserServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users3")
public class UserControllerEx3 {


    private final UserServiceInterface userService;

    // Dependency injeciton para el servicio de usuario
    public UserControllerEx3(UserServiceInterface userService) {
        this.userService = userService;
    }

    /********************************
     ********* GET (Step 1) *********
     ********************************/
    @GetMapping("/{dni}")
    public ResponseEntity<Persona> getPersonaByDni(@PathVariable String dni) {
        Persona persona = userService.getPersonaByDni(dni);
        return new ResponseEntity<>(persona, HttpStatus.OK);

        /*
        * no point in having this because the service already returns an error.
        * The type of error returned by the service and below are different
        * Look into this in the future
        if (persona != null) {
            // Devolver status OK si encontramos el usuario
            return new ResponseEntity<>(persona, HttpStatus.OK);
        }
        // Devolver status NOT FOUND si no se encuentra
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         */


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
        return new ResponseEntity<>(persona, HttpStatus.OK);

        /*
         * no point in having this because the service already returns an error.
         * The type of error returned by the service and below are different
         * Look into this in the future
        if (persona != null) {
            // Devolver status OK si encontramos el usuario
            return new ResponseEntity<>(persona, HttpStatus.OK);
        }
        // Devolver status NOT FOUND si no se encuentra
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         */
    }


    /********************************
     ****** DELETE (Optional) *******
     ********************************/
    @DeleteMapping("/{dni}")
    public ResponseEntity<Persona> deletePersonaByDni(@PathVariable String dni) {
        Persona persona = userService.deletePersona(dni);
        return new ResponseEntity<>(persona, HttpStatus.OK);

        /*
         * no point in having this because the service already returns an error.
         * The type of error returned by the service and below are different
         * Look into this in the future
        if (persona != null) {
            // Devolver status OK si encontramos el usuario
            return new ResponseEntity<>(persona, HttpStatus.OK);
        }
        // Devolver status NOT FOUND si no se encuentra
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         */
    }
}
