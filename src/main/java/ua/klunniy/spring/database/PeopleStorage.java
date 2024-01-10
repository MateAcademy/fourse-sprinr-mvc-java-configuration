package ua.klunniy.spring.database;

import ua.klunniy.spring.models.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Serhii Klunniy
 */
public class PeopleStorage {
    private static int peopleCount = 0;
    private static final List<Person> people;

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

    public static void save(Person person) {
        person.setId(++peopleCount);
        people.add(person);
    }

    public static void update(int id, Person person) {
        Person personInStorage = people.stream().filter(person1 -> person1.getId() == id).findAny().orElse(null);
        assert personInStorage != null;
        personInStorage.setName(person.getName());
        personInStorage.setSurname(person.getSurname());
        personInStorage.setEmail(person.getEmail());
        //people.set(id - 1, person);
    }

    public static void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }

}
