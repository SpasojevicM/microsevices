package com.example.orderservice.service;

import com.example.orderservice.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.threads.VirtualThreadExecutor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class OrderServiceImpl implements OrderService{

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public OrderServiceImpl(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void createOrder(Order order) throws JsonProcessingException {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            var name = order.getName();
            int LIMIT = 500;
            for (int i = 0; i < LIMIT; i++) {
                final int taskId = i;
                executor.submit(() -> {
                    try {
                        order.setName(name.concat(" ").concat(String.valueOf(taskId)));
                        kafkaTemplate.send("order-created", objectMapper.writeValueAsString(order));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

        }
    }
}
