package com.project.models.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.project.models.entity.Insured;


public interface InsuredRepository extends CrudRepository<Insured, Long> {
	Optional<Insured> findByNumberId(Long numberId);
}
