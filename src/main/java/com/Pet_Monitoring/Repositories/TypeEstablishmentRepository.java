package com.Pet_Monitoring.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.Pet_Monitoring.Entities.TypeEstablishment;

public interface TypeEstablishmentRepository extends CrudRepository<TypeEstablishment, Long> {
	Optional<TypeEstablishment> findByName(String typeEstablishmentName);

	boolean existsByName(String typeEstablishmentName);
}
