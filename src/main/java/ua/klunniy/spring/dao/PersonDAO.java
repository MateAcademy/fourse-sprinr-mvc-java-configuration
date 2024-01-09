package ua.klunniy.spring.dao;

import org.springframework.stereotype.Component;
import ua.klunniy.spring.database.PeopleStorage;
import ua.klunniy.spring.models.Person;

import java.util.List;

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

    public void save(Person person) {
        PeopleStorage.save(person);
    }

    public void update(int id, Person person) {
        PeopleStorage.update(id, person);
    }

}
