package ua.klunniy.spring.database;

import ua.klunniy.spring.models.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Serhii Klunniy
 */
public class PeopleStorage {
    private static int peopleCount = 0;
    private static List<Person> people;

    static {
        people = new ArrayList<>();

        people.add(new Person(++peopleCount, "Tom", "surname - Tom", "tom@gmail.com"));
        people.add(new Person(++peopleCount, "Bob", "surname - Bob", "bob@gmail.com"));
        people.add(new Person(++peopleCount, "Katy", "surname - Katy", "katy@gmail.com"));
        people.add(new Person(++peopleCount, "Mike", "surname - Mike", "mike@gmail.com"));
    }

    public static List<Person> getPeople() {
        return people;
    }

    public static void add(Person person) {
        person.setId(++peopleCount);
        people.add(person);
    }
}
