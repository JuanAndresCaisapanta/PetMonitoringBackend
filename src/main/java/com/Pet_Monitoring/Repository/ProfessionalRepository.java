package com.Pet_Monitoring.Repository;

import java.util.Optional;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Entity.Establishment;

@Repository
public interface ProfessionalRepository extends CrudRepository<Establishment, Integer> {

	Optional<Establishment> findByNombre(String nombre);

	boolean existsByNombre(String nombre);
	
}
