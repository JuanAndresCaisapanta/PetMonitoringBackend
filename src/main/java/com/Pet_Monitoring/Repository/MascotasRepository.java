package com.Pet_Monitoring.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Entity.Pet;

@Repository
public interface MascotasRepository extends JpaRepository<Pet, Integer> {

	Optional<Pet> findByNombre(String nombre);

	boolean existsByNombre(String nombre);


}
