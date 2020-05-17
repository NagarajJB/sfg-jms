package com.njb.sfgjms.consumer;

import javax.jms.Message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.njb.sfgjms.config.JmsConfig;
import com.njb.sfgjms.model.MyMessage;

@Component
public class MyConsumer {

	@JmsListener(destination = JmsConfig.QUEUE_NAME)
	public void listen(@Payload MyMessage myMessage, @Headers MessageHeaders headers, Message message) {
		System.out.println("Got a message!");
		System.out.println(myMessage);

	}

}
