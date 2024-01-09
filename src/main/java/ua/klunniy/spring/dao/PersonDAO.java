package ua.klunniy.spring.dao;

import org.springframework.stereotype.Component;
import ua.klunniy.spring.database.PeopleStorage;
import ua.klunniy.spring.models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Serhii Klunniy
 */
@Component
public class PersonDAO {

    public List<Person> index() {
        return PeopleStorage.getPeople();
    }

    public Person show(int id) {
        return PeopleStorage.getPeople().stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void add(Person person) {
        if (person != null) {
            PeopleStorage.add(person);
        }
    }
}
