package com.service.resultvotingssessionsservice.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum DecisionType {

	YES("yes"), NO("no");

	@Getter
	private String decisionValue;

}
