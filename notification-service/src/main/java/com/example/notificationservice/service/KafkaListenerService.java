package com.example.notificationservice.service;

import com.example.notificationservice.model.Order;
import com.example.notificationservice.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.BadRequestException;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    public void listener(ConsumerRecord<String, String> record) {
        try {
            var order = objectMapper.readValue(record.value(), Order.class);
            order.setCreatedAt(LocalDateTime.now());
            order.setPartition(record.partition());
            order.setTimestamp(record.timestamp());
            orderRepository.save(order);
            log.info("Message received {}", order);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

}
