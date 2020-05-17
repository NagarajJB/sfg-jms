package com.njb.sfgjms.producer;

import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.njb.sfgjms.config.JmsConfig;
import com.njb.sfgjms.model.MyMessage;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MyProducer {

	private final JmsTemplate jmsTemplate;
	private final ObjectMapper objectMapper;

	@Scheduled(fixedRate = 2000)
	public void sendMessage() {
		// System.out.println("Sending message!");
		MyMessage message = MyMessage.builder().id(UUID.randomUUID()).message("Hello from nag!").build();
		jmsTemplate.convertAndSend(JmsConfig.QUEUE_NAME, message);
		// System.out.println("Message sent!");
	}

	@Scheduled(fixedRate = 2000)
	public void sendAndReceiveMessage() throws JMSException {
		System.out.println("Sending message!");

		MyMessage myMessage = MyMessage.builder().id(UUID.randomUUID()).message("Hello from nag!").build();

		Message recievedMessage = jmsTemplate.sendAndReceive(JmsConfig.QUEUE_NAME_2, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {

				Message message;
				try {
					message = session.createTextMessage(objectMapper.writeValueAsString(myMessage));
					message.setStringProperty("_type", "com.njb.sfgjms.model.MyMessage"); // similar to
																							// converter.setTypeIdPropertyName("_type");
																							// in JmsConfig, inorder for
																							// reciver to deserialise
					return message;
				} catch (JsonProcessingException e) {
					throw new JMSException("Boom");
				}
			}
		});

		System.out.println("Message sent!");

		System.out.println(recievedMessage.getBody(String.class));

	}

}
