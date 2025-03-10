package com.futurespace.exercise.user.service;

import com.futurespace.exercise.exception.DuplicatePersonaException;
import com.futurespace.exercise.exception.PersonaNotFoundException;
import com.futurespace.exercise.user.model.Persona;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class UserService implements UserServiceInterface {

    // Mapa para almacenar usuarios en ej1 y ej2
    private final Map<String, Persona> users = new HashMap<>();

    // Lista para almacenar las personas en ej3
    private final List<Persona> personas = new ArrayList<>();

    // Constructor que inicializa la lista de personas con datos de ejemplo
    public UserService() {
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


    /**
     * Obtiene una persona por su DNI en la lista personas.
     *
     * @param dni El DNI de la persona a buscar.
     * @return La persona encontrada o null si no existe.
     */
    @Override
    public Persona getPersonaByDni(String dni) {
        return personas.stream()
                .filter(persona -> persona.getDNI().equalsIgnoreCase(dni))
                .findFirst()
                .orElseThrow(() -> new PersonaNotFoundException(dni));
    }


    /**
     * Actualiza la información de una persona dado su DNI
     * en la lista personas.
     *
     * @param dni            El DNI de la persona a actualizar.
     * @param updatedPersona La nueva información de la persona.
     * @return La persona actualizada o null si no se encontró.
     */
    @Override
    public Persona updatePersona(String dni, Persona updatedPersona) {
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getDNI().equalsIgnoreCase(dni)) {
                personas.set(i, updatedPersona);
                return updatedPersona;
            }
        }
        throw new PersonaNotFoundException(dni);
    }

    /**
     * Elimina una persona dado su DNI (tabla personas)
     *
     * @param dni El DNI de la persona a eliminar.
     * @return La persona eliminada o null si no se encontró.
     */
    @Override
    public Persona deletePersona(String dni) {
        Iterator<Persona> iterator = personas.iterator();
        while (iterator.hasNext()) {
            Persona persona = iterator.next();
            if (persona.getDNI().equalsIgnoreCase(dni)) {
                iterator.remove();
                return persona;
            }
        }
        throw new PersonaNotFoundException(dni);
    }

    /**
     * Crea un nuevo usuario en la tabla users.
     *
     * @param persona La información de la persona a crear.
     * @return La persona creada o null si ya existe un usuario con el mismo DNI.
     */
    @Override
    public Persona createUser(Persona persona) {
        if (users.containsKey(persona.getDNI())) {
            throw new DuplicatePersonaException(persona.getDNI());
        }
        users.put(persona.getDNI(), persona);
        return persona;
    }
}
