package com.futurespace.exercise.user;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class UserService {
    private final Map<String, Persona> users = new HashMap<>();
    private final List<Persona> personas = new ArrayList<>();

    public UserService() {
        // Initialize with sample personas
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

    public Persona getPersonaByDni(String dni) {
        return personas.stream()
                .filter(persona -> persona.getDNI().equalsIgnoreCase(dni))
                .findFirst()
                .orElse(null);
    }

    public Persona updatePersona(String dni, Persona updatedPersona) {
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getDNI().equalsIgnoreCase(dni)) {
                personas.set(i, updatedPersona);
                return updatedPersona;
            }
        }
        return null;
    }

    public Persona deletePersona(String dni) {
        Iterator<Persona> iterator = personas.iterator();
        while (iterator.hasNext()) {
            Persona persona = iterator.next();
            if (persona.getDNI().equalsIgnoreCase(dni)) {
                iterator.remove();
                return persona;
            }
        }
        return null;
    }

    public Persona createUser(Persona persona) {
        if (users.containsKey(persona.getDNI())) {
            return null; // Handle conflict in controller
        }
        users.put(persona.getDNI(), persona);
        return persona;
    }
}
