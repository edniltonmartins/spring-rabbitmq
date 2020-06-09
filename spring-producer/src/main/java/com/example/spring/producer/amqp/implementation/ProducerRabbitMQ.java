package com.example.spring.producer.amqp.implementation;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.spring.producer.amqp.AmqpProducer;
import com.example.spring.producer.dto.Message;

@Component
public class ProducerRabbitMQ implements AmqpProducer<Message>{

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value("spring.rabbit.request.routing-key.producer")
	private String queue;
	@Value("spring.rabbit.request.exchenge.producer")
	private String exchange;
	
	@Override
	public void producer(Message message) {
		try {
			rabbitTemplate.convertAndSend(exchange, queue, message);
			
		}
		catch(Exception ex) {
			throw new AmqpRejectAndDontRequeueException(ex);
		}
	}
	
	

}
