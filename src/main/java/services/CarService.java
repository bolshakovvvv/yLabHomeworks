package services;

import models.Car;
import repositories.CarRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CarService {

    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void addCar(Car car) {
        carRepository.addCar(car);
    }

    public Optional<Car> getCarById(int id) {
        return carRepository.getCarById(id);
    }

    public List<Car> getAllCars() {
        return carRepository.getAllCars();
    }

    public boolean updateCar(Car updatedCar) {
        return carRepository.updateCar(updatedCar);
    }

    public boolean deleteCar(int id) {
        return carRepository.deleteCar(id);
    }

    public List<Car> searchCarsByBrand(String brand) {
        return carRepository.getAllCars().stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    public List<Car> searchCarsByModel(String model) {
        return carRepository.getAllCars().stream()
                .filter(car -> car.getModel().equalsIgnoreCase(model))
                .collect(Collectors.toList());
    }

    public List<Car> searchCarsByYear(int year) {
        return carRepository.getAllCars().stream()
                .filter(car -> car.getYear() == year)
                .collect(Collectors.toList());
    }

    public List<Car> searchCarsByPrice(double minPrice, double maxPrice) {
        return carRepository.getAllCars().stream()
                .filter(car -> car.getPrice() >= minPrice && car.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    public List<Car> searchCarsByCondition(String condition) {
        return carRepository.getAllCars().stream()
                .filter(car -> car.getCondition().equalsIgnoreCase(condition))
                .collect(Collectors.toList());
    }
}
