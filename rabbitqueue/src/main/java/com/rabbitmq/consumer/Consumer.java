package com.rabbitmq.consumer;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.module.model.ICallable;
import com.module.model.IReceiver;
import com.rabbitmq.config.RabbitConfig;
import com.rabbitmq.config.RabbitProperties;


@Service
public class Consumer implements IReceiver {
	
	@Autowired
	RabbitProperties properties;
	@Autowired
	RabbitConfig config;
	ICallable callback;
	SimpleMessageListenerContainer listener;
	
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
	    container.setConnectionFactory(connectionFactory);
 	    container.setQueueNames(properties.getQUEUENAME());
	    container.setMessageListener(listenerAdapter);
	    container.setAcknowledgeMode(AcknowledgeMode.AUTO);
	    return container;
	}
	
	MessageListenerAdapter listenerAdapter(ICallable receiver) {
		return new MessageListenerAdapter(receiver, "receive");
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		listener = container(config.createConnectionFactory(), listenerAdapter(callback));
		listener.start();
	}

	@Override
	public void register(ICallable callback) {
		// TODO Auto-generated method stub
		this.callback = callback;
	}

}
