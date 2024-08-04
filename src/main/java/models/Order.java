package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {
    private int id;
    private String carBrand;
    private String carModel;
    private String customerUsername;
    private String status;
}
