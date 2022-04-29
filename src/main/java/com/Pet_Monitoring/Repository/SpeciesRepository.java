package com.Pet_Monitoring.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Entity.Species;

@Repository
public interface SpeciesRepository extends CrudRepository<Species, Integer> {
}
