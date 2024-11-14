package com.kairgaliyev.microservices.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//url is url of inventory service and feign = притворяться, симулировать
@FeignClient(value = "inventory", url = "http://localhost:8082")
public interface InventoryClient {

    //Обращаемся к энд пойнту в проекте inventory-service оп пути /api/inventory, обращаемся за счет Spring Cloud Open Feign
    @RequestMapping(method = RequestMethod.GET, value = "/api/inventory")
    boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);
}
