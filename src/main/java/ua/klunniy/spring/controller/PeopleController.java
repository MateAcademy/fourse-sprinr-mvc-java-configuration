package ua.klunniy.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

//  мы реализуем метод который будет возвращать список из людей
    @GetMapping
    public String index(Model model) {
// Получим всех людей из DAO и передадим на отображение в представление
        model.addAttribute("people", personService.index());
        return "/people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
// Получим одного человека по id из DAO и передадим на отображение в представление
        model.addAttribute("person", personService.show(id));
        return "/people/show";
    }

// по адресу /people/new вернется html форма создания человека

    @GetMapping("/new")
    public String newPeople(@ModelAttribute("person") Person person) {
        return "/people/new";
    }


    @PostMapping("/new")
    public String create(@ModelAttribute("person") Person person) {
        //Добавляем человека в БД
        personService.save(person);
        return "people/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personService.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person,
                         @PathVariable("id") int id) {
        personService.update(id, person);
        return "people/show";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/people";
    }


//    @GetMapping("/new")
//    public String newPeople(Model model) {
//        model.addAttribute("person", new Person());
//        return "/people/new";
//    }


// create
//    @PostMapping()
//    public String addNewPeopleInDB(@RequestParam(value = "name", required = false) String name,
//                                   @RequestParam(value = "surname", required = false) String surname,
//                                   @RequestParam(value = "email", required = false) String email,
//                                   Model model) {
//
//        Person person = new Person();
//
//        person.setName(name);
//        person.setSurname(surname);
//        person.setEmail(email);
//
//        model.addAttribute("person", person);
//
//        personService.add(name, surname, email);
//        return "redirect:/people";
//        return "people/show";
//    }

}
