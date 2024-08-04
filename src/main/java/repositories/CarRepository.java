package repositories;

import models.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarRepository {
    private List<Car> cars = new ArrayList<>();

    public int getSize() {
        return cars.size();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public List<Car> getAllCars() {
        return cars;
    }

    public Optional<Car> findCarByBrandAndModel(String brand, String model) {
        return cars.stream()
                .filter(car -> car.getBrand().equals(brand) && car.getModel().equals(model))
                .findFirst();
    }

    public boolean updateCar(Car updatedCar) {
        Optional<Car> carOpt = getCarById(updatedCar.getId());
        if (carOpt.isPresent()) {
            Car car = carOpt.get();
            car.setBrand(updatedCar.getBrand());
            car.setModel(updatedCar.getModel());
            car.setYear(updatedCar.getYear());
            car.setPrice(updatedCar.getPrice());
            car.setCondition(updatedCar.getCondition());
            return true;
        }
        return false;
    }

    public boolean deleteCar(int id) {
        return cars.removeIf(car -> car.getId() == id);
    }

    public Optional<Car> getCarById(int id) {
        return cars.stream().filter(car -> car.getId() == id).findFirst();
    }
}
