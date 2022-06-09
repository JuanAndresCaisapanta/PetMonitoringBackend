package com.Pet_Monitoring.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Entities.Medicine;

@Repository
public interface MedicineRepository extends CrudRepository<Medicine, Integer>{
	
	List<Medicine> findAllByPetId(int id);

}
