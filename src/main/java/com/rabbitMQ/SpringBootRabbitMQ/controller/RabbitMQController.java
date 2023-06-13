package com.rabbitMQ.SpringBootRabbitMQ.controller;

import com.rabbitMQ.SpringBootRabbitMQ.dto.Employee;
import com.rabbitMQ.SpringBootRabbitMQ.producer.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class RabbitMQController {
    private final ProducerService producer;

    @GetMapping("/sendMsg")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message)
    {
        producer.sendMessgage(message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/publish_message")
    public ResponseEntity<Employee> sendMessage(@RequestBody Employee employee)
    {
        producer.publish_message(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}
