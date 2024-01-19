package ua.klunniy.spring.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public void add100CarWithoutBatch() {
        long l1 = System.currentTimeMillis();
        carDao.addCarSimple(carList());
        long l2 = System.currentTimeMillis();
        System.out.println("Add 100 car (\"without Batch\") from: " + (l2 - l1) + " millisecond." );
    }

    public void clear() {
        carDao.clear();
    }

    public void add100CarWithBatch() {
        long l1 = System.currentTimeMillis();
        carDao.addCarBatch(carList());
        long l2 = System.currentTimeMillis();
        System.out.println("Add 100 car (\"with Batch\") from: " + (l2 - l1) + " millisecond." );
    }

    public List<Car> getCars() {
        return carDao.getAllCars();
    }

    private List<Car> carList() {
        List<Car> carList = new ArrayList<>(100);
        for (long i = 0; i < 100; i++) {
            Car car = new Car(i, "bmw" + (i + 1), "read");
            carList.add(car);
        }
        return carList;
    }

}
