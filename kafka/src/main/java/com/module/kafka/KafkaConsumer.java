package com.module.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.module.model.ICallable;
import com.module.model.IReceiver;


@Service
public class KafkaConsumer implements IReceiver {
	
	ICallable callback;
	
    private String kafkaTopic="hello";

    @SuppressWarnings("rawtypes")
	@KafkaListener(topics = "hello", groupId = "test-grp")
    public void receive(ConsumerRecord record) {
        System.out.println(String.format("Topic - %s, Partition - %d, Value: %s", kafkaTopic, 
        		record.partition(), record.value()));
        callback.receive(record.value());
    }

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void register(ICallable callback) {
		// TODO Auto-generated method stub
		this.callback = callback;
	}

}
