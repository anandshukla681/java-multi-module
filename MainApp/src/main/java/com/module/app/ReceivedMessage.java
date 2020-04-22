package com.module.app;

import org.springframework.stereotype.Component;

import com.module.model.ICallable;

@Component
public class ReceivedMessage implements ICallable {

	@Override
	public void receive(Object data) {
		System.out.println("message received < "+ data+" >");
	}	

}
