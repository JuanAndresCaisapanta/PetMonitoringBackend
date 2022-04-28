package com.Pet_Monitoring.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Entity.Professional;

@Repository
public interface EspecialidadesRepository extends JpaRepository<Professional, Integer> {

	Optional<Professional> findByNombre(String nombre);

	boolean existsByNombre(String nombre);

}
