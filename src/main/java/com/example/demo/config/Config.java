package com.example.demo.config;

import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

//@EnableJms
@Configuration
public class Config {
	
	@Value("${activemq.broker-url}")
	private String brokerUrl;
	
	@Bean
	public Queue queue()
	{
		return new ActiveMQQueue("incoming.queue");
	}
	
	@Bean
	public Queue queueOutgoing()
	{
		return new ActiveMQQueue("outgoing.queue");
	}
	
	@Bean
	public ActiveMQConnectionFactory activeMQConnectionFactory()
	{
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		factory.setBrokerURL(brokerUrl);
		return factory;
	}
	
	@Bean
	public JmsTemplate jmsTemplate()
	{
		return new JmsTemplate(activeMQConnectionFactory());
	}

}
