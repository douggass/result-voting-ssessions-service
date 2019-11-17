package com.service.resultvotingssessionsservice.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.service.resultvotingssessionsservice.service.SessionResultService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Consumer {

	@Autowired
	private SessionResultService sessionResultService;

	@KafkaListener(topics = "${cloudkarafka.topic}")
	public void processMessage(SessionMessageDto message) {
		log.info("message: {}", message);
		sessionResultService.addResult(message.getUuid(), message.getDecision());
	}

}
