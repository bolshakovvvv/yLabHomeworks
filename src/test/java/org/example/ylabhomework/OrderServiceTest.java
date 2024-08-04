package org.example.ylabhomework;

import static org.junit.jupiter.api.Assertions.*;

import models.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.OrderRepository;
import services.OrderService;

import java.util.Optional;

public class OrderServiceTest {

    private OrderService orderService;
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        orderRepository = new OrderRepository();
        orderService = new OrderService(orderRepository);
    }

    @Test
    public void testAddOrder() {
        Order order = new Order(1, "Toyota", "Corolla", "customer1", "Pending");
        orderService.addOrder(order);

        Optional<Order> retrievedOrder = orderRepository.getOrderById(1);
        assertTrue(retrievedOrder.isPresent());
        assertEquals("Toyota", retrievedOrder.get().getCarBrand());
    }

    @Test
    public void testUpdateOrder() {
        Order order = new Order(1, "Toyota", "Corolla", "customer1", "Pending");
        orderService.addOrder(order);

        Order updatedOrder = new Order(1, "Toyota", "Corolla", "customer1", "Completed");
        assertTrue(orderService.updateOrder(updatedOrder));

        Optional<Order> retrievedOrder = orderRepository.getOrderById(1);
        assertTrue(retrievedOrder.isPresent());
        assertEquals("Completed", retrievedOrder.get().getStatus());
    }

    @Test
    public void testDeleteOrder() {
        Order order = new Order(1, "Toyota", "Corolla", "customer1", "Pending");
        orderService.addOrder(order);

        assertTrue(orderService.deleteOrder(1));
        assertFalse(orderRepository.getOrderById(1).isPresent());
    }
}
