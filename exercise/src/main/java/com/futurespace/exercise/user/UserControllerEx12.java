package com.futurespace.exercise.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("users")
public class UserControllerEx12 {

    private Map<String, UserDetailsModel> users;



    /*******************************
     ********* Ejercicio 1 *********
     *******************************/

    // Crea un usuario de prueba y lo devuelve como UserDetailsModel.
    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserDetailsModel> getUser(@PathVariable String userId) {
        UserDetailsModel user = new UserDetailsModel();
        String uuid = UUID.randomUUID().toString();
        user.setId(uuid);
        user.setDNI("Y1234567A");
        user.setFirstName("Adam");
        user.setLastName("Kaawach");
        user.setMiddleName("Farid");
        user.setSex("Male");
        user.setBirthdate(LocalDate.of(2002, 9, 14));

        return new ResponseEntity<>(user, HttpStatus.OK);
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
    public ResponseEntity<UserDetailsModel> createUser(
            @RequestParam("dni") String dni,
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido1") String apellido1,
            @RequestParam("apellido2") String apellido2,
            @RequestParam("fechaNacimiento") String fechaNacimiento,
            @RequestParam("sexo") String sexo) {

        // Si el map usuarios no existe
        if (users == null) users = new HashMap<>();

        // si el usuario ya existe
        if (users.containsKey(dni)) return new ResponseEntity<>(users.get(dni), HttpStatus.CONFLICT);

        // Convertir la fecha de String a LocalDate (se espera el formato ISO: yyyy-MM-dd)
        LocalDate birthdate = LocalDate.parse(fechaNacimiento);

        // Crear y configurar el objeto con los datos recibidos
        UserDetailsModel user = new UserDetailsModel();
        user.setDNI(dni);
        user.setFirstName(nombre);
        user.setMiddleName(apellido1);
        user.setLastName(apellido2);
        user.setBirthdate(birthdate);
        user.setSex(sexo);
        String userId = UUID.randomUUID().toString();
        user.setId(userId);

        // Insertar usuario
        users.put(dni, user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}