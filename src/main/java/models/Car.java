package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Car {
    private int id;
    private String brand;
    private String model;
    private int year;
    private double price;
    private String condition;

    public Car(String brand, String model, int year, double price, String condition) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.condition = condition;
    }
}
