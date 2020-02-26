package com.example.demo.listener;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
public class Consumer {
	
	
	@Autowired
	Queue queueOutgoing;
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@JmsListener(destination = "incoming.queue")
	public void consume(String message)
	{
		jmsTemplate.convertAndSend(queueOutgoing, message);
		System.out.println("Received Message: " + message);
	}
}
