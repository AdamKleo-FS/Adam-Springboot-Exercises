package com.futurespace.exercise.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("users")
public class UserControllerEx12 {

    private final UserServiceInterface userService;

    public UserControllerEx12(UserServiceInterface userService) {
        this.userService = userService;
    }

    /*******************************
     ********* Ejercicio 1 *********
     *******************************/
    // Crea un usuario de prueba y lo devuelve como Persona.
    @GetMapping(path = "/{userId}")
    public ResponseEntity<Persona> getUser(@PathVariable String userId) {
        // Crear la persona
        Persona user = new Persona(
                "Y1234567A",
                "Adam",
                "Farid",
                "Kaawach",
                LocalDate.of(2002, 9, 14),
                "M");

        // Devolver OKAY
        return ResponseEntity.ok(user);
    }




    /*******************************
     ********* Ejercicio 2 *********
     *******************************/

    /**
     * Crear un nuevo usuario utilizando los datos del request
     * Si el usuario ya existe devuelve un conflicto.
     * Si no lo crea y almacena en el map.
     */
    @PostMapping(path = "envioFormulario")
    public ResponseEntity<Persona> createUser(
            @RequestParam("dni") String dni,
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido1") String apellido1,
            @RequestParam("apellido2") String apellido2,
            @RequestParam("fechaNacimiento") String fechaNacimiento,
            @RequestParam("sexo") String sexo) {

        // Create a better date parser
        LocalDate birthdate = LocalDate.parse(fechaNacimiento);

        // Crear la persona
        Persona user = new Persona(dni, nombre, apellido1, apellido2, birthdate, sexo);
        Persona createdUser = userService.createUser(user);

        if (createdUser != null) {
            // Devolver status OK si encontramos el usuario
            return new ResponseEntity<>(user, HttpStatus.OK);

        }
        // Devolver status NOT FOUND si no se encuentra
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}