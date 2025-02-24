package com.futurespace.exercise.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {

    private Map<String, UserDetailsModel> users;

    @GetMapping(path = "/{userId}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            })
    public ResponseEntity<UserDetailsModel> getUser() {
        UserDetailsModel user = new UserDetailsModel();
        String uuid = "1234ABCD";
        user.setId(uuid);
        user.setFirstName("Adam");
        user.setLastName("Kaawach");
        user.setMiddleName("Farid");
        user.setSex("Male");
        user.setBirthdate(LocalDate.of(2002, 9, 14));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(path = "envioFormulario")
    public ResponseEntity<UserDetailsModel> createUser(
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido1") String apellido1,
            @RequestParam("apellido2") String apellido2,
            @RequestParam("fechaNacimiento") String fechaNacimiento,
            @RequestParam("sexo") String sexo) {

        // If users store doesn't exist yet.
        if (users == null) users = new HashMap<>();

        // Convertir la fecha de String a LocalDate (se espera el formato ISO: yyyy-MM-dd)
        LocalDate birthdate = LocalDate.parse(fechaNacimiento);

        // Crear y configurar el objeto con los datos recibidos
        UserDetailsModel user = new UserDetailsModel();
        user.setFirstName(nombre);
        user.setMiddleName(apellido1);
        user.setLastName(apellido2);
        user.setBirthdate(birthdate);
        user.setSex(sexo);
        String userId = UUID.randomUUID().toString();
        user.setId(userId);

        users.put(userId, user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}