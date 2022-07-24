package com.Pet_Monitoring.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Entities.EstablishmentType;
import com.Pet_Monitoring.Entities.Pet;

@Repository
public interface PetRepository extends CrudRepository<Pet, Long> {

	List<Pet> findByOrderByIdAsc();
	
	List<Pet> findAllByUsersId(Long user_id);


}
