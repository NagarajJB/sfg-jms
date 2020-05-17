package com.njb.sfgjms.consumer;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.njb.sfgjms.config.JmsConfig;
import com.njb.sfgjms.model.MyMessage;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MyConsumer {

	private final JmsTemplate jmsTemplate;

	@JmsListener(destination = JmsConfig.QUEUE_NAME)
	public void listen(@Payload MyMessage myMessage, @Headers MessageHeaders headers, Message message, org.springframework.messaging.Message springMessage) {
		/*
		 * System.out.println("Got a message!"); System.out.println(myMessage);
		 */

	}

	@JmsListener(destination = JmsConfig.QUEUE_NAME_2)
	public void listenAndRespond(@Payload MyMessage myMessage, @Headers MessageHeaders headers, Message message)
			throws JmsException, JMSException {
		System.out.println("Got a message!");
		System.out.println(myMessage);

		jmsTemplate.convertAndSend(message.getJMSReplyTo(), "Reply from the listener!");

	}

}
