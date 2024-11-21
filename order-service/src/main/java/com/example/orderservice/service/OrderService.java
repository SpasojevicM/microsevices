package com.example.orderservice.service;

import com.example.orderservice.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface OrderService {

    void createOrder(Order order) throws JsonProcessingException;

}
