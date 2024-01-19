package ua.klunniy.spring.dao;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.klunniy.spring.models.Car;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Serhii Klunniy
 */
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarDao {

    final JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addCarSimple(List<Car> carList) {
        for (Car car : carList) {
            jdbcTemplate.update("insert into car(name, color) values(?, ?)",
                    car.getName(), car.getColor());
        }
    }

    public void clear() {
          jdbcTemplate.update("DELETE FROM car");
    }

    public void addCarBatch(List<Car> carList) {
            jdbcTemplate.batchUpdate("insert into car(name, color) values(?, ?)",
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                            preparedStatement.setString(1, carList.get(i).getName());
                            preparedStatement.setString(2, carList.get(i).getColor());
                        }

                        @Override
                        public int getBatchSize() {
                            return carList.size();
                        }
                    });
    }

    public List<Car> getAllCars() {
        return jdbcTemplate.query("select * from car order by id", new BeanPropertyRowMapper<>(Car.class));
    }

}
