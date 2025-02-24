package com.futurespace.exercise.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {


    @GetMapping(path="/{userId}",
            produces={
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

}
