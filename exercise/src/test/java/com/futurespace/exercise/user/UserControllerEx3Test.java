package com.futurespace.exercise.user;

import com.futurespace.exercise.exception.PersonaNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebMvcTest(UserControllerEx3.class)
class UserControllerEx3Test {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserServiceInterface userService;

    private Persona testPersona;

    @BeforeEach
    void setUp() {
        testPersona = new Persona("Y1234567A", "Adam", "Farid", "Kaawach", LocalDate.of(2002, 9, 14), "M");
    }

    @Test
    void getPersonaByDni_ShouldReturnPersona_WhenExists() throws Exception {
        when(userService.getPersonaByDni("Y1234567A")).thenReturn(testPersona);

        mockMvc.perform(get("/users3/Y1234567A"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Adam"))
                .andExpect(jsonPath("$.dni").value("Y1234567A")); // FIXED
    }

    @Test
    void getPersonaByDni_ShouldReturnNotFound_WhenNotExists() throws Exception {
        when(userService.getPersonaByDni("INVALID_DNI")).thenThrow(new PersonaNotFoundException("INVALID_DNI"));

        mockMvc.perform(get("/users3/INVALID_DNI"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deletePersonaByDni_ShouldReturnOk_WhenExists() throws Exception {
        when(userService.deletePersona("Y1234567A")).thenReturn(testPersona);

        mockMvc.perform(delete("/users3/Y1234567A"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Adam"));
    }

    @Test
    void deletePersonaByDni_ShouldReturnNotFound_WhenNotExists() throws Exception {
        Mockito.doThrow(new PersonaNotFoundException("INVALID_DNI")).when(userService).deletePersona("INVALID_DNI");

        mockMvc.perform(delete("/users3/INVALID_DNI"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updatePersonaByDni_ShouldReturnUpdatedPersona_WhenValid() throws Exception {
        Persona updatedPersona = new Persona("Y1234567A", "Updated", "User", "Kaawach", LocalDate.of(2002, 9, 14), "M");
        when(userService.updatePersona(Mockito.eq("Y1234567A"), any(Persona.class))).thenReturn(updatedPersona);

        mockMvc.perform(put("/users3/Y1234567A")
                        .contentType(APPLICATION_JSON)
                        .content("{\"dni\":\"Y1234567A\", \"firstName\":\"Updated\", \"middleName\":\"User\", \"lastName\":\"Kaawach\", \"birthdate\":\"2002-09-14\", \"sex\":\"M\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Updated"));
    }

    @Test
    void updatePersonaByDni_ShouldReturnNotFound_WhenPersonaNotExists() throws Exception {
        when(userService.updatePersona(Mockito.eq("INVALID_DNI"), any(Persona.class)))
                .thenThrow(new PersonaNotFoundException("INVALID_DNI"));

        mockMvc.perform(put("/users3/INVALID_DNI")
                        .contentType(APPLICATION_JSON)
                        .content("{\"dni\":\"Y1111111B\", \"firstName\":\"Test\", \"middleName\":\"User\", \"lastName\":\"Test\", \"birthdate\":\"2000-01-01\", \"sex\":\"M\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updatePersonaByDni_ShouldReturnBadRequest_WhenInvalidInput() throws Exception {
        mockMvc.perform(put("/users3/Y1234567A")
                        .contentType(APPLICATION_JSON)
                        .content("{\"dni\":\"\", \"firstName\":\"\", \"middleName\":\"\", \"lastName\":\"\", \"birthdate\":\"\", \"sex\":\"\"}")) // Invalid data
                .andExpect(status().isBadRequest());
    }
}
