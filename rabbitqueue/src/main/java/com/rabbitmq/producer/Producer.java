package com.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.module.model.ISender;
import com.rabbitmq.config.RabbitConfig;
import com.rabbitmq.config.RabbitProperties;


@Service
public class Producer implements ISender {
	
	@Autowired
	RabbitConfig config;
	
	RabbitTemplate template;
	@Autowired
	RabbitProperties properties;
	
	@Override
	public void send(Object data) {
		template.convertAndSend(properties.getEXCHANGENAME(), 
				properties.getQUEUENAME(), data);
		//template.waitForConfirms(1);
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		template = config.getTemplate();
	} 
}
