package ua.klunniy.spring.dao;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.klunniy.spring.models.Car;

import java.util.List;

/**
 * @author Serhii Klunniy
 */
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarDao {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addCarSimple(List<Car> carList) {
        for (int i = 0; i < 1000; i++) {

        }
    }

    public void clear() {

    }

    public void addCarBatch(List<Car> carList) {

    }

    public List<Car> getAllCars() {

        return null;
    }

}
