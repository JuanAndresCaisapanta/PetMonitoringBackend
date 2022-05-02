package com.Pet_Monitoring.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.Pet_Monitoring.Entities.TypeMedicine;

public interface TypeMedicineRepository extends CrudRepository<TypeMedicine, Integer> {

	Optional<TypeMedicine> findByName(String name);

	boolean existsByName(String name);
	
}
