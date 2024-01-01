package ua.klunniy.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Serhii Klunniy
 */

@Controller
public class HelloController {

    @GetMapping("/hello-world")
    public String sayHello() {
        return "say_hello";
    }

}
