package com.Pet_Monitoring.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Entities.Species;

@Repository
public interface SpeciesRepository extends CrudRepository<Species, Long> {

	Optional<Species> findByName(String species_name);

	boolean existsByName(String species_name);
	
	List<Species> findByOrderByIdAsc();
}
