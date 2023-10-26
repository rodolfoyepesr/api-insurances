package com.project.models.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.project.models.entity.Protection;


public interface ProtectionRepository extends CrudRepository<Protection, Long> {
	Optional<Protection> findByCode(Long code);
}
