package org.example.ylabhomework;

import static org.junit.jupiter.api.Assertions.*;

import models.ServiceRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.ServiceRequestRepository;
import services.ServiceRequestService;

import java.util.Optional;

public class ServiceRequestServiceTest {

    private ServiceRequestService serviceRequestService;
    private ServiceRequestRepository serviceRequestRepository;

    @BeforeEach
    public void setUp() {
        serviceRequestRepository = new ServiceRequestRepository();
        serviceRequestService = new ServiceRequestService(serviceRequestRepository);
    }

    @Test
    public void testAddServiceRequest() {
        ServiceRequest request = new ServiceRequest(1, "Toyota", "Corolla", "customer1", "Maintenance", "Pending");
        serviceRequestService.addServiceRequest(request);

        Optional<ServiceRequest> retrievedRequest = serviceRequestRepository.getServiceRequestById(1);
        assertTrue(retrievedRequest.isPresent());
        assertEquals("Toyota", retrievedRequest.get().getCarBrand());
    }

    @Test
    public void testUpdateServiceRequest() {
        ServiceRequest request = new ServiceRequest(1, "Toyota", "Corolla", "customer1", "Maintenance", "Pending");
        serviceRequestService.addServiceRequest(request);

        ServiceRequest updatedRequest = new ServiceRequest(1, "Toyota", "Corolla", "customer1", "Maintenance", "Completed");
        assertTrue(serviceRequestService.updateServiceRequest(updatedRequest));

        Optional<ServiceRequest> retrievedRequest = serviceRequestRepository.getServiceRequestById(1);
        assertTrue(retrievedRequest.isPresent());
        assertEquals("Completed", retrievedRequest.get().getStatus());
    }

    @Test
    public void testDeleteServiceRequest() {
        ServiceRequest request = new ServiceRequest(1, "Toyota", "Corolla", "customer1", "Maintenance", "Pending");
        serviceRequestService.addServiceRequest(request);

        assertTrue(serviceRequestService.deleteServiceRequest(1));
        assertFalse(serviceRequestRepository.getServiceRequestById(1).isPresent());
    }
}
