package ua.klunniy.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.klunniy.spring.dao.PersonDAO;
import ua.klunniy.spring.models.Person;
import ua.klunniy.spring.service.PersonService;

/**
 * @author Serhii Klunniy
 */
@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonService personService;

    @Autowired
    public PeopleController(PersonService personService) {
        this.personService = personService;
    }

    //    мы реализуем метод который будет возвращать список из людей
    @GetMapping
    public String index(Model model) {
// Получим всех людей из DAO и передадим на отображение в представление
        model.addAttribute("people", personService.index());
        return "/people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
// Получим одного человека по id из DAO и передадим на отображение в представление
        model.addAttribute("people", personService.show(id));
        return "/people/show";
    }

    @GetMapping("/new")
    public String newPeople() {
        return "/people/new";
    }


    @PostMapping()
    private String addNewPeopleInDB(@RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "surname", required = false) String surname,
                                    @RequestParam(value = "email", required = false) String email) {

        personService.add(name, surname, email);
        return "redirect:/people";
    }
}
