package com.njb.sfgjms.producer;

import java.util.UUID;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.njb.sfgjms.config.JmsConfig;
import com.njb.sfgjms.model.MyMessage;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MyProducer {

	private final JmsTemplate jmsTemplate;

	@Scheduled(fixedRate = 2000)
	public void sendMessage() {
		System.out.println("Sending message!");

		MyMessage message = MyMessage.builder().id(UUID.randomUUID()).message("Hello from nag").build();

		jmsTemplate.convertAndSend(JmsConfig.QUEUE_NAME, message);

		System.out.println("Message sent!");
		
	}

}
