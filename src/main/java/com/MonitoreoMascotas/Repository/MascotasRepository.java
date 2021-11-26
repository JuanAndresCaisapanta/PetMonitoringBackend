package com.MonitoreoMascotas.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MonitoreoMascotas.Entity.Mascotas;


@Repository
public interface MascotasRepository extends JpaRepository<Mascotas, Integer>{

	Optional<Mascotas> findByNombre(String nombre);
	boolean existsByNombre(String nombre);
	
	
}
