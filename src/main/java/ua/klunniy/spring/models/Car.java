package ua.klunniy.spring.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Serhii Klunniy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Car {

    @Min(value = 0, message = "Car id must be more 0")
    @NotNull(message = "Cars id shouldn't be null")
    Long id;

    @NotEmpty(message = "Cars name shouldn't be empty")
    @NotNull(message = "Cars name shouldn't be null")
    @Size(min = 2, max = 20, message = "Cars name must be from 2 to 20 characters")
    String name;

    @NotEmpty(message = "Cars color shouldn't be empty")
    @NotNull(message = "Cars color shouldn't be null")
    @Size(min = 2, max = 20, message = "Cars color must be from 2 to 20 characters")
    String color;

    public Car(String name, String color) {
        this.name = name;
        this.color = color;
    }

}
