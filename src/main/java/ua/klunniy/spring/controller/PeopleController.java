package ua.klunniy.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.klunniy.spring.models.Person;
import ua.klunniy.spring.service.PersonService;

/**
 * REST описывает то какие URLы и HTTP методы у нас должны быть для взаимодействия с данными
 *
 * С GET запросом вот по этому URL мы получим все записи:
 * GET /posts Получаем все записи(READ)
 *
 * GET /posts/:id Получаем одну запись(READ)
 * DELETE /posts/:id Удаляем запись(DELETE)
 *
 * GET /posts/new HTML форма создания записи
 * POST /posts Создаем новую запись(CREATE)
 *
 * GET /posts/:id/edit HTML форма редактирования записи
 * PATCH /posts/:id Обновляем запись(UPDATE)
 *
 * */

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonService personService;

    @Autowired
    public PeopleController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("people", personService.index());
        return "/people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.show(id));
        return "/people/show";
    }

    @GetMapping("/new")
    public String newPeople(@ModelAttribute("person") Person person) {
        return "/people/new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("person") Person person,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/new";
        }

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
                         @PathVariable("id") int id,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/new";
        }
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
