package services;

import models.ServiceRequest;
import repositories.ServiceRequestRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class ServiceRequestService {

    private ServiceRequestRepository serviceRequestRepository;

    public ServiceRequestService(ServiceRequestRepository serviceRequestRepository) {
        this.serviceRequestRepository = serviceRequestRepository;
    }

    public void addServiceRequest(ServiceRequest serviceRequest) {
        serviceRequestRepository.addServiceRequest(serviceRequest);
    }

    // Получить заявку на обслуживание по идентификатору
    public Optional<ServiceRequest> getServiceRequestById(int id) {
        return serviceRequestRepository.getServiceRequestById(id);
    }

    public List<ServiceRequest> getAllServiceRequests() {
        return serviceRequestRepository.getAllServiceRequests();
    }

    public boolean updateServiceRequest(ServiceRequest updatedServiceRequest) {
        return serviceRequestRepository.updateServiceRequest(updatedServiceRequest);
    }

    public boolean deleteServiceRequest(int id) {
        return serviceRequestRepository.deleteServiceRequest(id);
    }

    public List<ServiceRequest> searchServiceRequestsByCustomerUsername(String username) {
        return serviceRequestRepository.getAllServiceRequests().stream()
                .filter(request -> request.getCustomerUsername().equalsIgnoreCase(username))
                .collect(Collectors.toList());
    }

    public List<ServiceRequest> searchServiceRequestsByServiceType(String serviceType) {
        return serviceRequestRepository.getAllServiceRequests().stream()
                .filter(request -> request.getServiceType().equalsIgnoreCase(serviceType))
                .collect(Collectors.toList());
    }

    public List<ServiceRequest> searchServiceRequestsByStatus(String status) {
        return serviceRequestRepository.getAllServiceRequests().stream()
                .filter(request -> request.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }
}
