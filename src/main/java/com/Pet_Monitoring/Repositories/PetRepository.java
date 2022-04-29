package com.Pet_Monitoring.Repositories;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Entities.Pet;

@Repository
public interface PetRepository extends CrudRepository<Pet, Integer> {

	Optional<Pet> findByNombre(String nombre);

	boolean existsByNombre(String nombre);


}
