package com.rabbitMQ.SpringBootRabbitMQ.consumer;

import com.rabbitMQ.SpringBootRabbitMQ.dto.Employee;
import com.rabbitMQ.SpringBootRabbitMQ.producer.ProducerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerRabbitMQ {
    private final RabbitTemplate rabbitTemplate; // use to send the message to the queue in rabbitMQ
    private final Logger LOGGER = LoggerFactory.getLogger(ProducerService.class);

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void getMessage(String message)
    {
        LOGGER.info("Consumer get message: {} from RabbitMQ", message);
    }

    @RabbitListener(queues = "${rabbitmq.queue.json.name}")
    public void getMessageJson(Employee message)
    {
        LOGGER.info("Consumer get message: {} from RabbitMQ", message.toString());
    }

}
