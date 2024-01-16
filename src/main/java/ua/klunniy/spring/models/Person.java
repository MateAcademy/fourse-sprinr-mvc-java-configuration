package ua.klunniy.spring.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @author Serhii Klunniy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Person {

    int id;

    @NotNull(message = "Name shouldn't be not null")
    @Size(min = 2, max = 30, message = "Size name shouldn't be more 2 and less 30 characters")
    String name;

    @NotNull(message = "Surname shouldn't be not null")
    @Size(min = 2, max = 30, message = "Size name shouldn't be more 2 and less 30 characters")
    String surname;

    @Size(min = 0, max = 150, message = "Age must be more then 0 and less 150")
    int age;

    @NotNull(message = "Email shouldn't be not null")
    @Size(min = 2, max = 30, message = "Size email shouldn't be more 2 and less 30 characters")
    String email;

    public Person(String name, String surname, int age, String email) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
    }

    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Person(int id, String name, String surname, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

}
