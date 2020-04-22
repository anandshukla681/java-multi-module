package com.module.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.module.model.ICallable;
import com.module.model.IReceiver;

@Component
public class Receive implements CommandLineRunner {
	
	@Autowired
	IReceiver receiver;
	boolean isstarted = false;
	@Autowired 
	ICallable callback;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		if(!isstarted) {
			isstarted = true;
			receiver.register(callback);
			receiver.init();
		}
		
	}

}
