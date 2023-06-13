package com.rabbitMQ.SpringBootRabbitMQ.producer.impl;

import com.rabbitMQ.SpringBootRabbitMQ.dto.Employee;
import com.rabbitMQ.SpringBootRabbitMQ.producer.ProducerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerServiceImpl implements ProducerService {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routing_key;

    @Value("${rabbitmq.routing.key.json}")
    private String routing_key_json;

    private final RabbitTemplate rabbitTemplate; // use to send the message to the queue in rabbitMQ

    private final Logger LOGGER = LoggerFactory.getLogger(ProducerService.class);

    @Override
    public void sendMessgage(String message)
    {
        LOGGER.info("Send message: {} to the exchange: {} with  routingkey: {}", message, exchange, routing_key);
        rabbitTemplate.convertAndSend(exchange, routing_key, message);
    }

    @Override
    public void publish_message(Employee employee) {
        LOGGER.info("Send message: {} to the exchange: {} with  routingkey: {}", employee, exchange, routing_key_json);
        rabbitTemplate.convertAndSend(exchange, routing_key_json, employee);
    }
}
