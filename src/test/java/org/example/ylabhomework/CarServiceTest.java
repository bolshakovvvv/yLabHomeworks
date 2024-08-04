package org.example.ylabhomework;

import models.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.CarRepository;
import services.CarService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CarServiceTest {

    private CarService carService;

    @BeforeEach
    public void setUp() {
        CarRepository carRepository = new CarRepository();
        carService = new CarService(carRepository);
    }

    @Test
    public void testAddCar() {
        Car car = new Car("Toyota", "Camry", 2020, 30000, "New");
        carService.addCar(car);
        List<Car> cars = carService.getAllCars();
        assertEquals(1, cars.size());
        assertEquals("Toyota", cars.get(0).getBrand());
    }

    @Test
    public void testUpdateCar() {
        Car car = new Car("Toyota", "Camry", 2020, 30000, "New");
        carService.addCar(car);
        car.setBrand("Honda");
        carService.updateCar(car);
        Optional<Car> updatedCar = carService.getCarById(car.getId());
        assertTrue(updatedCar.isPresent());
        assertEquals("Honda", updatedCar.get().getBrand());
    }

    @Test
    public void testDeleteCar() {
        Car car = new Car("Toyota", "Camry", 2020, 30000, "New");
        carService.addCar(car);
        boolean deleted = carService.deleteCar(car.getId());
        assertTrue(deleted);
        List<Car> cars = carService.getAllCars();
        assertEquals(0, cars.size());
    }

    @Test
    public void testGetAllCars() {
        carService.addCar(new Car("Toyota", "Camry", 2020, 30000, "New"));
        carService.addCar(new Car("Honda", "Accord", 2019, 25000, "Used"));
        List<Car> cars = carService.getAllCars();
        assertEquals(2, cars.size());
    }
}
