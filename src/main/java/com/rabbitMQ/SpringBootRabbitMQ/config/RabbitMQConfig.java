package com.rabbitMQ.SpringBootRabbitMQ.config;

import org.springframework.amqp.core.*;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.queue.json.name}")
    private String queue_json;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routing_key;

    @Value("${rabbitmq.routing.key.json}")
    private String routing_key_json;

    //Config spring bean for queue
    @Bean
    public Queue queue()
    {
        return new Queue(queue);
    }

    //Config spring bean for queue to send Message in JSON
    @Bean
    public Queue queueJson()
    {
        return new Queue(queue_json);
    }

    //Config spring bean for exchange
    @Bean
    public TopicExchange exchange()
    {
        return new TopicExchange(exchange);
    }

    //Config spring bean for exchange the message to queue with the routing key
    @Bean
    public Binding binding()
    {
        return BindingBuilder.bind(queue()).to(exchange()).with(routing_key);
    }

    //Config spring bean for exchange the message in JSON to queue with the routing key
    @Bean
    public Binding bindingJson()
    {
        return BindingBuilder.bind(queueJson()).to(exchange()).with(routing_key_json);
    }

    //Config the message can be with Json
    @Bean
    public MessageConverter messageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
