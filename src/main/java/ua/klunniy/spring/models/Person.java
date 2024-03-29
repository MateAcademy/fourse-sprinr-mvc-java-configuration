package ua.klunniy.spring.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

/**
 * @author Serhii Klunniy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @NotEmpty(message = "Surname should not be empty")
    @Size(min = 2, max = 30, message = "Surname should be between 2 and 30 characters")
    private String surname;

    @Min(value = 0, message = "Age should be greater than 0")
    private int age;

    @NotEmpty(message = "Email should not be empty")
    @Size(min = 2, max = 30, message = "Email should be between 2 and 30 characters")
    @Email(message ="Email should be valid")
    private String email;

//Страна, Город, Индекс(6 цифр)
    @NotEmpty(message = "Address should not be empty")
    @Size(min = 6, max = 30, message = "Address should be between 6 and 30 characters")
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message = "Your address should be in this format: Country, City, Postal Code (6 digits)")
    private String address;

    public Person(String name, String surname, int age, String email, String address) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.age = age;
        this.address = address;
    }

}
