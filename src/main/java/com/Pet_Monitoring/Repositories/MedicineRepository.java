package com.Pet_Monitoring.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Entities.Medicine;

@Repository
public interface MedicineRepository extends CrudRepository<Medicine, Integer>{
	
	/*
	 * Optional<Medicine> findByFabricador(String fabricador);
	 * 
	 * boolean existsByFabricador(String fabricador);
	 */

}
