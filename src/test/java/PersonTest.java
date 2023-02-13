import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    /**
     * Comprobamos que la salida esperada al llamar al metodo averageAgePerGender con una lista nula es lanzar
     * una excepcion del tipo IllegalArgumentException
     */
    @Test
    public void averageAgePerGenderForNullList(){
        person = new Person("Pepe", 21, "Male");

        assertThrows(IllegalArgumentException.class, () -> person.averageAgePerGender(null));
    }

    /**
     * Comprobamos que al llamar al metodo para una lista vacia nos indica que ambas medias son -1, que es el valor
     * que indica que no habia personas de ese genero
     */
    @Test
    public void averageAgePerGenderForEmptyList(){
        person = new Person("Pepe", 21, "Male");
        double[] expectedResult = {-1.0,-1.0};

        assertArrayEquals(expectedResult, person.averageAgePerGender(new ArrayList<Person>()));
    }

    /**
     * Comprobamos que para un solo hombre, la media sera la edad de ese hombre y la media de las mujeres sera -1
     */
    @Test
    public void averageAgePerGenderForOneMaleList(){
        person = new Person("Pepe", 21, "Male");
        List<Person> people = new ArrayList<>();
        people.add(person);
        double[] expectedResult = {21.0,-1.0};

        assertArrayEquals(expectedResult, person.averageAgePerGender(people));
    }

    /**
     * Comprobamos que para una sola mujer, la media sera la edad de esa mujer y la media de los hombres sera -1
     */
    @Test
    public void averageAgePerGenderForOneFemaleList(){
        person = new Person("Pepe", 21, "Male");
        List<Person> people = new ArrayList<>();
        people.add(new Person("Carla", 45, "Female"));
        double[] expectedResult = {-1.0,45.0};

        assertArrayEquals(expectedResult, person.averageAgePerGender(people));
    }

    /**
     * Comprobamos que la media de los hombres sera la media de todas las edades, mientras que la de las mujeres
     * sera -1
     */
    @Test
    public void averageAgePerGenderForOnlyMaleList(){
        person = new Person("Pepe", 21, "Male");
        List<Person> people = new ArrayList<>();
        people.add(person);
        people.add(new Person("Carlos", 37, "Male"));
        people.add(new Person("Santiago", 54, "Male"));
        people.add(new Person("Eugenio", 98, "Male"));
        double[] expectedResult = {52.5,-1.0};

        assertArrayEquals(expectedResult, person.averageAgePerGender(people));
    }

    /**
     * Comprobamos que la media de las mujeres sera la media de todas las edades, mientras que la de los hombres sera
     * -1
     */
    @Test
    public void averageAgePerGenderForOnlyFemaleList(){
        person = new Person("Sandra", 12, "Female");
        List<Person> people = new ArrayList<>();
        people.add(person);
        people.add(new Person("Maria", 6, "Female"));
        people.add(new Person("Marta", 43, "Female"));
        people.add(new Person("Carla", 71, "Female"));
        double[] expectedResult = {-1.0,33.0};

        assertArrayEquals(expectedResult, person.averageAgePerGender(people));
    }

    /**
     * Comprobamos que la media tanto de los hombres como de las mujeres seran la edad del hombre y de la mujer
     * respectivamente
     */
    @Test
    public void averageAgePerGenderForOneMaleAndOneFemaleList(){
        person = new Person("Abril", 89, "Female");
        List<Person> people = new ArrayList<>();
        people.add(person);
        people.add(new Person("Andres", 12, "Male"));
        double[] expectedResult = {12.0,89.0};

        assertArrayEquals(expectedResult, person.averageAgePerGender(people));
    }

    /**
     * En los siguientes tests comprobamos que ocurre con distintas combinaciones de hombres y mujeres,
     * viendo que las edades medias calculadas se corresponden con las que esperamos recibir
     */
    @Test
    public void averageAgePerGenderForTwoMaleAndOneFemaleList(){
        person = new Person("Sandra", 12, "Female");
        List<Person> people = new ArrayList<>();
        people.add(person);
        people.add(new Person("Paco", 43, "Male"));
        people.add(new Person("Gervasio", 67, "Male"));
        double[] expectedResult = {55.0,12.0};

        assertArrayEquals(expectedResult, person.averageAgePerGender(people));
    }

    @Test
    public void averageAgePerGenderForOneMaleAndTwoFemaleList(){
        person = new Person("Sandra", 12, "Female");
        List<Person> people = new ArrayList<>();
        people.add(person);
        people.add(new Person("Paco", 43, "Male"));
        people.add(new Person("Marina", 67, "Female"));
        double[] expectedResult = {43.0,39.5};

        assertArrayEquals(expectedResult, person.averageAgePerGender(people));
    }

    @Test
    public void averageAgePerGenderForFourMaleAndFourFemaleList(){
        person = new Person("Sandra", 12, "Female");
        List<Person> people = new ArrayList<>();
        people.add(person);
        people.add(new Person("Paco", 43, "Male"));
        people.add(new Person("Gervasio", 67, "Male"));
        people.add(new Person("Marta", 76, "Female"));
        people.add(new Person("Juan", 23, "Male"));
        people.add(new Person("Marina", 21, "Female"));
        people.add(new Person("Carmen", 78, "Female"));
        people.add(new Person("Carlos", 56, "Male"));
        double[] expectedResult = {47.25,46.75};

        assertArrayEquals(expectedResult, person.averageAgePerGender(people));
    }
}
