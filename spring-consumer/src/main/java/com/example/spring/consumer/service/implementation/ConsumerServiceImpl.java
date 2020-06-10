package com.example.spring.consumer.service.implementation;

import org.springframework.stereotype.Service;

import com.example.spring.consumer.dto.Message;
import com.example.spring.consumer.service.ConsumerService;

@Service
public class ConsumerServiceImpl implements ConsumerService {

	@Override
	public void action(Message message) {
		System.out.println(message);
	}

}
