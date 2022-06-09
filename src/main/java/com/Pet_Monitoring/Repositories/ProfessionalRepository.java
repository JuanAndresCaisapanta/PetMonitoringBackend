package com.Pet_Monitoring.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Entities.Professional;

@Repository
public interface ProfessionalRepository extends CrudRepository<Professional, Integer> {

	List<Professional> findAllByPetId(int id);
	
}
