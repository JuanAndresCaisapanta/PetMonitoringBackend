package com.MonitoreoMascotas.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MonitoreoMascotas.Entity.Dispositivos;

@Repository
public interface DispositivosRepository extends JpaRepository<Dispositivos, Integer>{
	
	Optional<Dispositivos> findByNombre(String nombre);

	boolean existsByNombre(String nombre);

}
