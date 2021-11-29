package com.MonitoreoMascotas.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MonitoreoMascotas.Entity.Especialidades;

@Repository
public interface EspecialidadesRepository extends JpaRepository<Especialidades, Integer>{
	
	Optional<Especialidades> findByNombre(String nombre);
	boolean existsByNombre(String nombre);

}
