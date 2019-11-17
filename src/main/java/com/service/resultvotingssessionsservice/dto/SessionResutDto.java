package com.service.resultvotingssessionsservice.dto;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionResutDto {

	@JsonProperty("sessionId")
	private UUID id;

	@JsonProperty("sessionStartDate")
	private Instant start;

	@JsonProperty("sessionEndDate")
	private Instant end;

	@JsonProperty("sessionClose")
	private Boolean isClose;

	@JsonProperty("sessionSubjectDescription")
	private String subjectDescription;

	@JsonProperty("sessionAmountMinutes")
	private Integer amountMinutes;

	@JsonProperty("sessionPositiveVotes")
	private Integer positiveVotes;

	@JsonProperty("sessionNegativeVotes")
	private Integer negativeVotes;

}