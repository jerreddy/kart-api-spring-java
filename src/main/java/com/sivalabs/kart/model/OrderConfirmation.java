package com.sivalabs.kart.model;

import com.sivalabs.kart.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderConfirmation {
    private String orderId;
    private Order.OrderStatus orderStatus;
}
