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

    public void addNewOrder(Orders orders) {
        Optional<Orders> potentialOrder = orderRepository
                .findById(orders.getId());
        if (potentialOrder.isPresent()) {
            throw new IllegalStateException(
                    "Orders of id " + orders.getId() + " already exists!");
        }
        orderRepository.save(orders);
    }

    public List<Orders> getOrders() { return orderRepository.findAll(); }

    public Optional<Orders> getOrder(Long id) { return orderRepository.findById(id);}

    public void deleteOrder(Long id) {
        Optional<Orders> potentialOrder = orderRepository.findById(id);
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
        Orders orders = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Orders with id " + id + " does not exist!"
                ));

        if (newTireName != null &&
        newTireName.length() > 0 &&
        !Objects.equals(orders.getTireName().toLowerCase(), newTireName.toLowerCase())) {
            orders.setTireName(newTireName);
        }

        if (newAmount != null &&
            !Objects.equals(orders.getAmount(), newAmount)) {
            orders.setAmount(newAmount);
        }
    }

    public Orders getOneOrder(Long id) {
        Orders order = orderRepository.findById(id).get();
        return order;
    }
}
