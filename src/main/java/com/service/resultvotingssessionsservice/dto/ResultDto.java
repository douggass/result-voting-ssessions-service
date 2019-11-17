package com.service.resultvotingssessionsservice.dto;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("session-result")
public class ResultDto {

	@Id
	private String id;

	@Indexed
	private UUID uuid;

	private Integer yes;
	private Integer no;

}
