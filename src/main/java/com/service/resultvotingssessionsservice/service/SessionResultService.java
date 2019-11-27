package com.service.resultvotingssessionsservice.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.resultvotingssessionsservice.dto.ResultDto;
import com.service.resultvotingssessionsservice.dto.SessionResutDto;
import com.service.resultvotingssessionsservice.repository.ResultRepository;
import com.service.resultvotingssessionsservice.type.DecisionType;

@Service
public class SessionResultService {

	@Autowired
	private ResultRepository resultRepository;

	private static final Integer VOTE_WEIGHT = 1;
	private static final Integer ZERO = 0;

	// TODO: implement method call to fetch more session information
	public SessionResutDto getResult(final UUID uuid) {
		return Optional.ofNullable(resultRepository.findByUuid(uuid)).map(item -> SessionResutDto.builder()
				.id(item.getUuid()).negativeVotes(item.getNo()).positiveVotes(item.getYes()).build()).orElse(null);
	}

	// TODO: refactor implementation
	public void addResult(final String uuid, final String decision) {
		Optional.ofNullable(resultRepository.findByUuid(UUID.fromString(uuid))).ifPresentOrElse(result -> {
			if (DecisionType.NO.getDecisionValue().equals(decision)) {
				result = result.toBuilder().no(result.getNo() + VOTE_WEIGHT).build();
			}
			if (DecisionType.YES.getDecisionValue().equals(decision)) {
				result = result.toBuilder().yes(result.getYes() + VOTE_WEIGHT).build();
			}
			this.resultRepository.save(result);
		}, () -> this.resultRepository.save(ResultDto.builder().uuid(UUID.fromString(uuid))
				.no(DecisionType.NO.getDecisionValue().equals(decision) ? VOTE_WEIGHT : ZERO)
				.yes(DecisionType.YES.getDecisionValue().equals(decision) ? VOTE_WEIGHT : ZERO).build()));
	}

}