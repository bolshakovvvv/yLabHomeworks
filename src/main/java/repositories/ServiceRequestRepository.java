package repositories;

import models.ServiceRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServiceRequestRepository {

    private List<ServiceRequest> serviceRequests;

    public int getSize(){
        return serviceRequests.size();
    }

    public ServiceRequestRepository() {
        this.serviceRequests = new ArrayList<>();
    }

    public void addServiceRequest(ServiceRequest serviceRequest) {
        serviceRequests.add(serviceRequest);
    }

    public Optional<ServiceRequest> getServiceRequestById(int id) {
        return serviceRequests.stream().filter(request -> request.getId() == id).findFirst();
    }

    public List<ServiceRequest> getAllServiceRequests() {
        return new ArrayList<>(serviceRequests);
    }

    public boolean updateServiceRequest(ServiceRequest updatedServiceRequest) {
        Optional<ServiceRequest> requestOpt = getServiceRequestById(updatedServiceRequest.getId());
        if (requestOpt.isPresent()) {
            ServiceRequest request = requestOpt.get();
            request.setCarBrand(updatedServiceRequest.getCarBrand());
            request.setCarModel(updatedServiceRequest.getCarModel());
            request.setCustomerUsername(updatedServiceRequest.getCustomerUsername());
            request.setServiceType(updatedServiceRequest.getServiceType());
            request.setStatus(updatedServiceRequest.getStatus());
            return true;
        }
        return false;
    }

    public boolean deleteServiceRequest(int id) {
        return serviceRequests.removeIf(request -> request.getId() == id);
    }
}
