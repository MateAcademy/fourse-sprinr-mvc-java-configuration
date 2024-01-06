package ua.klunniy.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Serhii Klunniy
 */
@Controller
public class CalculateController {

    @GetMapping("/calculator")
    public String calculate(@RequestParam(value = "a", required = false) Double a,
                            @RequestParam(value = "b", required = false) Double b,
                            @RequestParam(value = "action", required = false) String action,
                            Model model) throws TEAppExeption {

        try {
            double rez = 0D;
            switch (action) {
                case "multiplication" -> rez = a * b;
                case "addition" -> rez = a + b;
                case "subtraction" -> rez = a - b;
                case "division" -> rez = a / b;
                default -> throw new TEAppExeption("not correct !!!!");
            }

            model.addAttribute("result", rez);
            return "calc/calculator";
        } catch (Exception e) {
            throw new TEAppExeption("mistake");
        }
    }

    class TEAppExeption extends Exception {
        String value;

        public TEAppExeption(String value) {
            this.value = value;
        }
    }

}
