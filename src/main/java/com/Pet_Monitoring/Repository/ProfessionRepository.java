package com.Pet_Monitoring.Repository;

import java.util.Optional;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Entity.Professional;

@Repository
public interface ProfessionRepository extends CrudRepository<Professional, Integer> {

	Optional<Professional> findByNombre(String nombre);

	boolean existsByNombre(String nombre);

}
