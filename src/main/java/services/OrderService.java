package services;

import models.Order;
import repositories.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void addOrder(Order order) {
        orderRepository.addOrder(order);
    }

    public Optional<Order> getOrderById(int id) {
        return orderRepository.getOrderById(id);
    }

    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    public boolean updateOrder(Order updatedOrder) {
        return orderRepository.updateOrder(updatedOrder);
    }

    public boolean deleteOrder(int id) {
        return orderRepository.deleteOrder(id);
    }

    public List<Order> searchOrdersByCustomerUsername(String username) {
        return orderRepository.getAllOrders().stream()
                .filter(order -> order.getCustomerUsername().equalsIgnoreCase(username))
                .collect(Collectors.toList());
    }

    public List<Order> searchOrdersByStatus(String status) {
        return orderRepository.getAllOrders().stream()
                .filter(order -> order.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }
}
