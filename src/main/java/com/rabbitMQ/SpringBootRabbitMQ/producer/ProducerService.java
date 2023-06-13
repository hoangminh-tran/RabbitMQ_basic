package com.rabbitMQ.SpringBootRabbitMQ.producer;

import com.rabbitMQ.SpringBootRabbitMQ.dto.Employee;

public interface ProducerService {
    public void sendMessgage(String message);

    public void publish_message(Employee employee);
}
