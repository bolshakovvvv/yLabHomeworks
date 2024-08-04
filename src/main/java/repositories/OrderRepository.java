package repositories;

import models.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepository {

    private List<Order> orders;

    public int getSize(){
        return orders.size();
    }

    public OrderRepository() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public Optional<Order> getOrderById(int id) {
        return orders.stream().filter(order -> order.getId() == id).findFirst();
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    public boolean updateOrder(Order updatedOrder) {
        Optional<Order> orderOpt = getOrderById(updatedOrder.getId());
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            order.setCarBrand(updatedOrder.getCarBrand());
            order.setCarModel(updatedOrder.getCarModel());
            order.setCustomerUsername(updatedOrder.getCustomerUsername());
            order.setStatus(updatedOrder.getStatus());
            return true;
        }
        return false;
    }

    public boolean deleteOrder(int id) {
        return orders.removeIf(order -> order.getId() == id);
    }
}
