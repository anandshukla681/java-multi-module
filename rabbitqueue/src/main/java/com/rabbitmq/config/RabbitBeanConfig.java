package com.rabbitmq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.module.model.IReceiver;
import com.module.model.ISender;
import com.rabbitmq.consumer.Consumer;
import com.rabbitmq.producer.Producer;


@Configuration
public class RabbitBeanConfig {
	
	@Bean
	public ISender getProducer() {
		return  new Producer();
	}
	
	@Bean 
	public RabbitConfig getConfig() {
		return new RabbitConfig();
	}
	
	@Bean 
	RabbitProperties properties() {
		return new RabbitProperties();
	}
	
	@Bean
	public IReceiver getConsumer() {
		return new Consumer();
	}
	

}
