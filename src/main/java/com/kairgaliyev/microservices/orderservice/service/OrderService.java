package com.kairgaliyev.microservices.orderservice.service;

import com.kairgaliyev.microservices.orderservice.client.InventoryClient;
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
    private final InventoryClient inventoryClient;

    public void placeOrder(OrderRequest orderRequest) {
        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        // by Spring Cloud openFeign we connect to projects and sending messages between this endpoints
        if (isProductInStock) {
            // map OrderRequest to Order object
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(orderRequest.quantity());
            // save order to OrderRepository
            orderRepository.save(order);
        } else {
            throw new RuntimeException("Product with SkuCode" + orderRequest.skuCode() + "is not in stock");
        }
    }
}