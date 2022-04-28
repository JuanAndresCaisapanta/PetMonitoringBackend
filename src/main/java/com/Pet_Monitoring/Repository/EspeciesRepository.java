package com.Pet_Monitoring.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Entity.Species;

@Repository
public interface EspeciesRepository extends JpaRepository<Species, Integer> {

	Optional<Species> findByNombre(String nombre);

	boolean existsByNombre(String nombre);

}
