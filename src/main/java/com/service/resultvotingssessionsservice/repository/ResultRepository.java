package com.service.resultvotingssessionsservice.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.service.resultvotingssessionsservice.dto.ResultDto;

public interface ResultRepository extends CrudRepository<ResultDto, String> {

	public ResultDto findByUuid(UUID uuid);

}
