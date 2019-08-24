package com.sivalabs.kart.web.controller;

import com.sivalabs.kart.entity.Order;
import com.sivalabs.kart.model.OrderConfirmation;
import com.sivalabs.kart.repo.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@Slf4j
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public OrderConfirmation save(@RequestBody Order order) {
        order.setOrderId(UUID.randomUUID().toString());
        order.getItems().forEach(lineItem -> lineItem.setOrder(order));
        Order savedOrder = this.orderRepository.save(order);
        log.info("Created Order ID=" + savedOrder.getId() + ", ref_num=" + savedOrder.getOrderId());
        return new OrderConfirmation(savedOrder.getOrderId(), savedOrder.getStatus());
    }

    @GetMapping
    public List<Order> allOrders() {
        return this.orderRepository.findAll();
    }

    @GetMapping("/{orderId}")
    public Order getById(@PathVariable String orderId) {
        log.info("Getting order by id: {}", orderId);
        return this.orderRepository.findByOrderId(orderId).orElse(null);
    }

    @DeleteMapping("/{orderId}")
    public void cancel(@PathVariable String orderId) {
        Order order = this.orderRepository.findByOrderId(orderId).orElse(null);
        if (order == null) return;

        if (order.getStatus() == Order.OrderStatus.DELIVERED) {
            throw new RuntimeException("Order is already delivered");
        }
        order.setStatus(Order.OrderStatus.CANCELLED);
        orderRepository.save(order);
    }
}
