package com.Pet_Monitoring.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Entity.Medicine;

@Repository
public interface MedicineRepository extends CrudRepository<Medicine, Integer>{
	
	Optional<Medicine> findByFabricador(String fabricador);

	boolean existsByFabricador(String fabricador);

}
