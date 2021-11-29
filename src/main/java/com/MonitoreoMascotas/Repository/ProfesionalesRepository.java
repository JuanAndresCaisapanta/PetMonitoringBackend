package com.MonitoreoMascotas.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MonitoreoMascotas.Entity.Profesionales;
@Repository
public interface ProfesionalesRepository extends JpaRepository<Profesionales, Integer>{
	
	Optional<Profesionales> findByNombre(String nombre);
	boolean existsByNombre(String nombre);

}
