package com.kairgaliyev.microservices.orderservice.service;

import com.kairgaliyev.microservices.orderservice.dto.OrderRequest;
import com.kairgaliyev.microservices.orderservice.model.Order;

import com.kairgaliyev.microservices.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        // map OrderRequest to Order object
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(orderRequest.price());
        order.setSkuCode(orderRequest.skuCode());
        order.setQuantity(orderRequest.quantity());
        // save order to OrderRepository
        orderRepository.save(order);
    }
}