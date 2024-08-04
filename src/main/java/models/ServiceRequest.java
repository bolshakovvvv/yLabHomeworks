package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceRequest {
    private int id;
    private String carBrand;
    private String carModel;
    private String customerUsername;
    private String serviceType;  // Тип обслуживания, например "Обычное обслуживание", "Ремонт", "Технический осмотр"
    private String status;
}
