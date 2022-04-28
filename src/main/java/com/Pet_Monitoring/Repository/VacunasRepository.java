package com.Pet_Monitoring.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Entity.Medicine;

@Repository
public interface VacunasRepository extends JpaRepository<Medicine, Integer>{
	
	Optional<Medicine> findByFabricador(String fabricador);

	boolean existsByFabricador(String fabricador);

}
