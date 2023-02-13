import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PersonTest {
    private Person person;

    @AfterEach
    public void shutdown(){
        person = null;
    }

    /**
     * Deberemos comprobar que al introducir una edad negativa, el constructor elevara una excepcion
     */
    @Test
    public void constructingPersonNegativeAgeShouldThrowIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> new Person("Pepe", -21, "Male"));
    }

    /**
     * A su vez tambien deberemos comprobar que si la edad es 0 tambien se eleve la misma excepcion
     */
    @Test
    public void constructingPersonAgeZeroShouldThrowIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> new Person("Carmen", 0, "Female"));
    }

    /**
     * Si intentamos crear una persona con un genero distinto a "Male" o "Female" tambien debera lanzar una excepcion
     */
    @Test
    public void constructingPersonIncorrectGenderShouldThrowIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> new Person("Maria", 10, "Other"));
    }

    /**
     * Comprobamos que recibimos el nombre de forma correcta
     */
    @Test
    public void gettingNameOfPerson(){
        person = new Person("Pepe", 21, "Male");

        assertEquals(person.name(), "Pepe");
    }

    /**
     * Comprobamos que recibimos la edad de forma correcta
     */
    @Test
    public void gettingAgeOfPerson(){
        person = new Person("Juan", 40, "Male");

        assertEquals(person.age(), 40);
    }

    /**
     * Comprobamos que al crear a un hombre, recibimos el genero que esperamos
     */
    @Test
    public void gettingGenderOfMale(){
        person = new Person("Gervasio", 93, "Male");

        assertEquals(person.gender(), "Male");
    }

    /**
     * Comprobamos que al crear a una mujer, recibimos el genero que esperamos
     */
    @Test
    public void gettingGenderOfFemale(){
        person = new Person("Sandra", 45, "Female");

        assertEquals(person.gender(), "Female");
    }
}
