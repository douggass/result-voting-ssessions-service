package com.service.resultvotingssessionsservice.controller;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.service.resultvotingssessionsservice.dto.SessionResutDto;
import com.service.resultvotingssessionsservice.service.SessionResultService;

@RestController
public class ResultController {

	@Autowired
	private SessionResultService sessionResultService;

	@GetMapping(value = "/v1/session/{sessionId}/result", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SessionResutDto> getResult(@Valid @PathVariable("sessionId") UUID sessionUUID) {
		return Optional.ofNullable(sessionResultService.getResult(sessionUUID)).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

}