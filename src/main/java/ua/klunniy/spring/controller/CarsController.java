package ua.klunniy.spring.controller;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.klunniy.spring.service.CarService;

/**
 * @author Serhii Klunniy
 */
@Controller
@RequestMapping("/multiple")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarsController {

    final CarService carService;

    public CarsController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/simple")
    public String multipleQuery(Model model) {
        carService.add1000CarSimple();
        model.addAttribute("cars", carService.getCars());
        return "multiple/cars";
    }

    @GetMapping("/clear")
    public String clear(Model model) {
        carService.clear();
        model.addAttribute("cars", carService.getCars());
        return "multiple/cars";
    }

    @GetMapping("/butchProcessor")
    public String multipleQueryButchProcessor(Model model) {
        carService.add1000CarBatchProcessor();
        model.addAttribute("cars", carService.getCars());
        return "multiple/cars";
    }

}
