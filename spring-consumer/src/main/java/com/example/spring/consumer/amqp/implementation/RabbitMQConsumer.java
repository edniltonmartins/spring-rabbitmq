package com.example.spring.consumer.amqp.implementation;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.spring.consumer.amqp.AmqpConsumer;
import com.example.spring.consumer.dto.Message;
import com.example.spring.consumer.service.ConsumerService;

@Component
public class RabbitMQConsumer implements AmqpConsumer<Message> {

	@Autowired
	private ConsumerService service;
	
	@Override
	@RabbitListener(queues="spring.rabbit.request.routing-key.producer")
	public void consumer(Message message) {
		service.action(message);
	}

}
