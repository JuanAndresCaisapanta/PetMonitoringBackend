package com.Pet_Monitoring.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.Pet_Monitoring.Entities.Breed;

public interface BreedRepository extends CrudRepository<Breed, Long> {
	Optional<Breed> findByName(String name);

	boolean existsByName(String name);
}
