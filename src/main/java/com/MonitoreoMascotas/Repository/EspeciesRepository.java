package com.MonitoreoMascotas.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MonitoreoMascotas.Entity.Especies;

@Repository
public interface EspeciesRepository extends JpaRepository<Especies, Integer> {

	Optional<Especies> findByNombre(String nombre);

	boolean existsByNombre(String nombre);

}
