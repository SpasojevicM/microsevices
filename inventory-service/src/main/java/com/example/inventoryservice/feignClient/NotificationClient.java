package com.example.inventoryservice.feignClient;

import com.example.inventoryservice.model.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("notification-service")
public interface NotificationClient {

    @GetMapping("orders")
    ResponseEntity<List<Order>> getOrders();

}
