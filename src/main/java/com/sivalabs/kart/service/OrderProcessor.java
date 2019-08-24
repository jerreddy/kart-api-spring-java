package com.sivalabs.kart.service;

import com.sivalabs.kart.entity.Order;
import com.sivalabs.kart.repo.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderProcessor {

    private final OrderRepository orderRepository;

    public OrderProcessor(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Scheduled(fixedDelay = 2 * 60 * 1000)
        //every 2 minutes
    void processOrders() {
        List<Order> orders = orderRepository.findByStatus(Order.OrderStatus.NEW);
        if (orders.isEmpty()) {
            log.info("No new orders to be processed");
            return;
        }
        for (Order order : orders) {
            log.info("Processing order {} ", order.getOrderId());
            order.setStatus(Order.OrderStatus.DELIVERED);
            orderRepository.save(order);
            log.info("Order {} is delivered", order.getOrderId());
        }
    }
}
