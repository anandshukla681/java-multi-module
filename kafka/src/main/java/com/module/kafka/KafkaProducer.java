package com.module.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.module.model.ISender;


@Service
public class KafkaProducer implements ISender{

	int i = 0;
	@Autowired
	private KafkaTemplate<String, String> template;

	private String kafkaTopic="hello";
	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void send(Object data) {
		// TODO Auto-generated method stub
		template.send(kafkaTopic,Integer.toString(++i),  "test message - " + data );
	}


}
