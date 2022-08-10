package com.Pet_Monitoring.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.Pet_Monitoring.Entities.EstablishmentType;

public interface EstablishmentTypeRepository extends CrudRepository<EstablishmentType, Long> {
	Optional<EstablishmentType> findByName(String establishmentType_name);

	boolean existsByName(String establishmentType_name);
	
	List<EstablishmentType> findByOrderByIdAsc();
}
