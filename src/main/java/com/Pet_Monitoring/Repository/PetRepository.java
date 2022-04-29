package com.Pet_Monitoring.Repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Entity.Pet;

@Repository
public interface PetRepository extends CrudRepository<Pet, Integer> {

	Optional<Pet> findByNombre(String nombre);

	boolean existsByNombre(String nombre);


}
