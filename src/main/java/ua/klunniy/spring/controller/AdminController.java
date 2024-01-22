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
@RequestMapping("/admin")
public class AdminController {
    private final PersonService personService;

    @Autowired
    public AdminController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String adminPage(Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("people", personService.index());
        return "adminPage";
    }

    @PatchMapping("/add")
    public String makeAdmin(@ModelAttribute("person") Person person) {
        System.out.println(person.getId());
        return "redirect:/people";
    }

}
