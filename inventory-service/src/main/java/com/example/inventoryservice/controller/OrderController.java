package com.example.inventoryservice.controller;

import com.example.inventoryservice.feignClient.NotificationClient;
import com.example.inventoryservice.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class OrderController {

    private final NotificationClient notificationClient;

    public OrderController(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return notificationClient.getOrders();
    }

}
