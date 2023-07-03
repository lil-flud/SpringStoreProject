package com.example.SpringTireStoreProject.Orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) { this.orderService = orderService; }

    @GetMapping
    public List<Orders> getOrders() { return orderService.getOrders(); }

    @GetMapping(path = "{id}")
    public Optional<Orders> getOrder(@PathVariable("id") Long id) {
        return orderService.getOrder(id);
    }

    @PostMapping
    public void registerNewOrder(@RequestBody Orders orders) {
        orderService.addNewOrder(orders);
    }

    @DeleteMapping(path = "delOrder/{orderId}")
    public void deleteOrder(@PathVariable("orderId") Long id) {
        orderService.deleteOrder(id);
    }

    @PutMapping(path = "updateOrder/{orderId}")
    public void updateOrder(@PathVariable("orderId") Long id,
                            @RequestParam(required = false) String newTireName,
                            @RequestParam(required = false) Integer newAmount) {
        orderService.updateOrder(id, newTireName, newAmount);
    }
}
