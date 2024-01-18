package ua.klunniy.spring.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ua.klunniy.spring.dao.CarDao;
import ua.klunniy.spring.models.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Serhii Klunniy
 */
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarService {

    final CarDao carDao;

    @Autowired
    public CarService(CarDao carDao) {
        this.carDao = carDao;
    }

    public void add1000CarSimple() {
        carDao.addCarSimple(carList());
    }

    public void clear() {
        carDao.clear();
    }

    public void add1000CarBatchProcessor() {
        carDao.addCarBatch(carList());
    }

    public List<Car> getCars() {
        return carDao.getAllCars();
    }

    private List<Car> carList() {
        List<Car> carList = new ArrayList<>(1000);
        for (long i = 0; i < 1000; i++) {
            Car car = new Car(i, "bmw" + i, "read" + i);
            carList.add(car);
        }
        return carList;
    }

}
