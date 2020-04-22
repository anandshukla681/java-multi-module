package com.module.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.module.model.ISender;
import com.rabbitmq.config.RabbitBeanConfig;

/*@SpringBootApplication(scanBasePackages = "com.module.kafka")*/
@SpringBootApplication
@Import(RabbitBeanConfig.class)
@EnableScheduling
public class MainAppApplication {

	@Autowired
	ISender sender;
	boolean isstarted = false;
	@Scheduled(fixedDelay = 1000L)
	public void send() {
		if(!isstarted) {
			sender.init();
			isstarted = true;
		}
		String message = "hello spring boot";
		sender.send(message);
		System.out.println("Sent Message "+ message);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MainAppApplication.class, args);
	}

}
