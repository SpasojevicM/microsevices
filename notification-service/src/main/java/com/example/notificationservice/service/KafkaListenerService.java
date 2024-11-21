package com.example.notificationservice.service;

import com.example.notificationservice.model.Order;
import com.example.notificationservice.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.BadRequestException;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class KafkaListenerService {

    private final ObjectMapper objectMapper;
    private final OrderRepository orderRepository;

    public KafkaListenerService(ObjectMapper objectMapper, OrderRepository orderRepository) {
        this.objectMapper = objectMapper;
        this.orderRepository = orderRepository;
    }

    @KafkaListener(topics = "order-created", groupId = "notification-group")
    public void listener(String message) {
        try {
            var order = objectMapper.readValue(message, Order.class);
            orderRepository.save(order);
            log.info("Message received {}", message);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

}
