package com.rabbitmq.config;

import org.springframework.amqp.core.AbstractExchange;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.BindingBuilder.GenericArgumentsConfigurer;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class RabbitConfig {
	/*@Autowired
	ConfirmCallback callback;*/
	@Autowired
	RabbitProperties properties;

	public ConnectionFactory createConnectionFactory(){
	    CachingConnectionFactory connectionFactory = new CachingConnectionFactory("127.0.0.1", 5672);
	    connectionFactory.setUsername("guest");
	    connectionFactory.setPassword("guest");
	    connectionFactory.setVirtualHost("/");
	    /*connectionFactory.setPublisherReturns(true);
	    connectionFactory.setPublisherConfirmType(ConfirmType.SIMPLE);*/
	    return connectionFactory;
	}
	
	@Bean
	Queue queue() {
		return new Queue(properties.getQUEUENAME(), true);
	}
	
	@Bean
	AbstractExchange exchange() {
		switch(properties.getEXCHANGETYPE()) {
		case "fanout":
			return new FanoutExchange(properties.getEXCHANGENAME());
		case "topic":
			return new TopicExchange(properties.getEXCHANGENAME());
		case "header":
			return new HeadersExchange(properties.getEXCHANGENAME());
		case "direct":
			return new DirectExchange(properties.getEXCHANGENAME());
		default:
			return null;
		}
	}
	
	@Bean
	GenericArgumentsConfigurer binding(Queue queue, AbstractExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(properties.getROUTINGKEY());
	}
	
	private RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
	    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        /*rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(callback);*/
	    return rabbitTemplate;
	}
	
	public RabbitTemplate getTemplate() {
		return rabbitTemplate(createConnectionFactory());
	}

}
