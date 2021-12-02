package com.MonitoreoMascotas.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MonitoreoMascotas.Entity.Vacunas;

@Repository
public interface VacunasRepository extends JpaRepository<Vacunas, Integer>{
	
	Optional<Vacunas> findByFabricador(String fabricador);

	boolean existsByFabricador(String fabricador);

}
