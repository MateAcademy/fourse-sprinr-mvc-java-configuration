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
@RequestMapping("/cars")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarsController {

    final CarService carService;

    public CarsController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/show")
    public String show(Model model) {
        model.addAttribute("cars", carService.getCars());
        return "multiple/cars";
    }

    @GetMapping("/add")
    public String withoutBatch(Model model) {
        carService.add100CarWithoutBatch();
        model.addAttribute("cars", carService.getCars());
        return "multiple/cars";
    }

    @GetMapping("/clear")
    public String clear(Model model) {
        carService.clear();
        model.addAttribute("cars", carService.getCars());
        return "multiple/cars";
    }

    @GetMapping("/addButch")
    public String queryWithBatch(Model model) {
        carService.add100CarWithBatch();
        model.addAttribute("cars", carService.getCars());
        return "multiple/cars";
    }

}
