package com.Pet_Monitoring.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.Pet_Monitoring.Entities.MedicineType;

public interface MedicineTypeRepository extends CrudRepository<MedicineType, Long> {

	Optional<MedicineType> findByName(String medicineType_name);

	boolean existsByName(String medicineType_name);
	
	List<MedicineType> findByOrderByIdAsc();
	
}
