package com.example.spring.producer.configuration;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class RabbitConfiguration {
	
	@Autowired
	private ConnectionFactory connectionFactory;
	
	/** Bean que cria uma fabrica de conexoes com template e converter já setado e prontos **/
	@Bean
	public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory() {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setMessageConverter(jackson2JsonMessageConverter());
		return factory;
	}
	
	/** Bean que cria um template de mensagem para o Rabbit **/
	@Bean
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
		return rabbitTemplate;
	}
	
	/** Bean que cria um padrão de converter para as mensagens utilizando jackson **/
	@Bean
	Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
		final ObjectMapper mapper = Jackson2ObjectMapperBuilder
				.json()
				.modules(new JavaTimeModule())
				.dateFormat(new StdDateFormat())
				.build();
		return new Jackson2JsonMessageConverter(mapper);
	}

}
