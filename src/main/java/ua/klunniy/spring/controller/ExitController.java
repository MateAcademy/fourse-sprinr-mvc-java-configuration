package ua.klunniy.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Serhii Klunniy
 */
@Controller
public class ExitController {

    @GetMapping("/exit")
    public String exit() {
        return "exit";
    }

}
