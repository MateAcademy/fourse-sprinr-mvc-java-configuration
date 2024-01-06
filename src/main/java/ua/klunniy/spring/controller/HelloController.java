package ua.klunniy.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Serhii Klunniy
 */

@Controller
@RequestMapping(value = "/first")
public class HelloController {

//Этот объект содержит в себе все данные об http запросе
    @GetMapping("/hello")
    public String helloPage(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        String str = "ми прийняли в Get запросі такі параметри: " + name + ", " + surname;
        System.out.println(str);
        model.addAttribute("text", str);

        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodbyePage(@RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "surname", required = false) String surname,
                              Model model) {
        System.out.println("goodbye" + name + surname);

        model.addAttribute("calc", "goodbye=" + name + ", "+ surname);
        return "first/goodbye";
    }

}
