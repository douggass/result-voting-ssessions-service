package com.service.resultvotingssessionsservice.service;

import static org.mockito.BDDMockito.given;

import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.service.resultvotingssessionsservice.dto.ResultDto;
import com.service.resultvotingssessionsservice.dto.SessionResutDto;
import com.service.resultvotingssessionsservice.repository.ResultRepository;

@SpringBootTest
public class SessionResultServiceTest {

	@Mock
	private ResultRepository resultRepository;

	@InjectMocks
	private SessionResultService sessionResultService;

	private static final UUID ANY_UUID = UUID.fromString("2eab1bdc-1359-45b2-aaf7-db3c2a909831");

	private static final Integer DECISION_NO = 5000;
	private static final Integer DECISION_YES = 9000;

	@Test
	public void getResultTest() {

		given(resultRepository.findByUuid(ANY_UUID))
				.willReturn(ResultDto.builder().uuid(ANY_UUID).no(DECISION_NO).yes(DECISION_YES).build());

		ResponseEntity<SessionResutDto> response = sessionResultService.getResult(ANY_UUID);

		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());

		SessionResutDto sessionResult = response.getBody();

		Assertions.assertThat(sessionResult.getPositiveVotes()).isEqualTo(DECISION_YES);
		Assertions.assertThat(sessionResult.getNegativeVotes()).isEqualTo(DECISION_NO);
		Assertions.assertThat(sessionResult.getId()).isEqualTo(ANY_UUID);

	}

}