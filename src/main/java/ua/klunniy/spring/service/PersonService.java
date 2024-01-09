package ua.klunniy.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.klunniy.spring.dao.PersonDAO;
import ua.klunniy.spring.models.Person;

import java.util.List;

/**
 * @author Serhii Klunniy
 */
@Service
public class PersonService {
    private final PersonDAO personDAO;

    @Autowired
    public PersonService(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public List<Person> index() {
        return personDAO.index();
    }

    public Person show(int id) {
        return personDAO.show(id);
    }

    public void add(String name, String surname, String email) {
        if (name != null && surname != null && email != null) {
            personDAO.add(new Person(name, surname, email));
        }
    }

}
