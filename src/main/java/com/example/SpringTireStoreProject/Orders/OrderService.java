package com.example.SpringTireStoreProject.Orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void addNewOrder(Order order) {
        Optional<Order> potentialOrder = orderRepository
                .findById(order.getId());
        if (potentialOrder.isPresent()) {
            throw new IllegalStateException(
                    "Order of id " + order.getId() + " already exists!");
        }
        orderRepository.save(order);
    }

    public List<Order> getOrders() { return orderRepository.findAll(); }

    public Optional<Order> getOrder(Long id) { return orderRepository.findById(id);}

    public void deleteOrder(Long id) {
        Optional<Order> potentialOrder = orderRepository.findById(id);
        if (potentialOrder.isPresent()) {
            orderRepository.deleteById(id);
        } else {
            throw new IllegalStateException(
                    "No order with an id of " + " exists!"
            );
        }
    }

    public void updateOrder(Long id,
                            String newTireName,
                            Integer newAmount) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Order with id " + id + " does not exist!"
                ));

        if (newTireName != null &&
        newTireName.length() > 0 &&
        !Objects.equals(order.getTireName().toLowerCase(), newTireName.toLowerCase())) {
            order.setTireName(newTireName);
        }

        if (newAmount != null &&
            !Objects.equals(order.getAmount(), newAmount)) {
            order.setAmount(newAmount);
        }
    }
}
