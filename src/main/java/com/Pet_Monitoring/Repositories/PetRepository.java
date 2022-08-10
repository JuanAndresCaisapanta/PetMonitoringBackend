package com.Pet_Monitoring.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Entities.Pet;

@Repository
public interface PetRepository extends CrudRepository<Pet, Long> {

	@Query(value = "SELECT p FROM Pet p, Users u WHERE p.users.id =u.id and u.status =true ORDER BY p.id  asc")
	List<Pet> findPetByOrderByIdAsc();
	
	List<Pet> findAllByUsersId(Long user_id);


}
