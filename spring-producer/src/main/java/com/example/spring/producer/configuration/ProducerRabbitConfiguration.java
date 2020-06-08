package com.example.spring.producer.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerRabbitConfiguration {

	@Value("spring.rabbit.request.routing-key.producer")
	private String queue;
	@Value("spring.rabbit.request.exchenge.producer")
	private String exchange;
	@Value("spring.rabbit.request.deadletter.producer")
	private String deadLetter;
	
	@Bean
	DirectExchange exchange() {
		return new DirectExchange(exchange);
	}
	
	@Bean
	Queue deadLetter() {
		return new Queue(deadLetter);
	}
	
	@Bean
	Queue queue() {
		Map<String, Object> args= new HashMap<>();
		args.put("x-dead-letter-exchange", exchange);
		args.put("x-dead-letter-routing-key", deadLetter);
		return new Queue(queue, true, false, false, args);
	}
	
	@Bean
	public Binding bindQueue() {
		return BindingBuilder.bind(queue())
				.to(exchange()).with(queue);
	}
	
	@Bean
	public Binding bindDeadLetter() {
		return BindingBuilder.bind(deadLetter())
				.to(exchange()).with(deadLetter);
	}
}
