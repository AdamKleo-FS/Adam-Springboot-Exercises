package com.futurespace.exercise.user;

import com.futurespace.exercise.exception.DuplicatePersonaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserControllerEx12.class)
class UserControllerEx12Test {

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
    void getUser_ShouldReturnMockedPersona() throws Exception {
        mockMvc.perform(get("/users/Y1234567A"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Adam"))
                .andExpect(jsonPath("$.dni").value("Y1234567A"));
    }

    @Test
    void createUser_ShouldReturnOk_WhenValidData() throws Exception {
        when(userService.createUser(any(Persona.class))).thenReturn(testPersona);

        mockMvc.perform(post("/users/envioFormulario")
                        .param("dni", "Y1234567A")
                        .param("nombre", "Adam")
                        .param("apellido1", "Farid")
                        .param("apellido2", "Kaawach")
                        .param("fechaNacimiento", "2002-09-14")
                        .param("sexo", "M"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Adam"));
    }

    @Test
    void createUser_ShouldReturnConflict_WhenDuplicateUser() throws Exception {
        when(userService.createUser(any(Persona.class))).thenThrow(new DuplicatePersonaException("Y1234567A"));

        mockMvc.perform(post("/users/envioFormulario")
                        .param("dni", "Y1234567A")
                        .param("nombre", "Adam")
                        .param("apellido1", "Farid")
                        .param("apellido2", "Kaawach")
                        .param("fechaNacimiento", "2002-09-14")
                        .param("sexo", "M"))
                .andExpect(status().isConflict());
    }

    @Test
    void createUser_ShouldReturnBadRequest_WhenInvalidInput() throws Exception {
        mockMvc.perform(post("/users/envioFormulario")
                        .param("dni", "")
                        .param("nombre", "")
                        .param("apellido1", "Farid")
                        .param("apellido2", "Kaawach")
                        .param("fechaNacimiento", "")
                        .param("sexo", "X"))
                .andExpect(status().isBadRequest());
    }
}
