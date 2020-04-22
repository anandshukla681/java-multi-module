package com.rabbitmq.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitProperties {
	@Value("${exchange.type}")
	private String EXCHANGE_TYPE;
	
	@Value("${exchange.name}")
	private String EXCHANGE_NAME;
	
	@Value("${queue.name}")
	private String QUEUE_NAME;
	
	@Value("${routing.key}")
	private String ROUTING_KEY;
	
	public String getEXCHANGETYPE() {
		return EXCHANGE_TYPE;
	}

	public String getEXCHANGENAME() {
		return EXCHANGE_NAME;
	}

	public String getQUEUENAME() {
		return QUEUE_NAME;
	}

	public String getROUTINGKEY() {
		return ROUTING_KEY;
	}
}
