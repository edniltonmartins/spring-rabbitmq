package com.example.spring.producer.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.producer.amqp.AmqpProducer;
import com.example.spring.producer.dto.Message;
import com.example.spring.producer.service.AmqpService;
import com.rabbitmq.client.AMQP;

@Service
public class RabbitMQService implements AmqpService, AMQP {
	
	@Autowired
	private AmqpProducer<Message> amqp;
	
	@Override
	public void sendToCustomer(Message message) {
		amqp.producer(message);
	}
	
	

}
