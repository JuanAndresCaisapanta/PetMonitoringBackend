package com.Pet_Monitoring.Repositories;

import java.util.List;
import java.util.Optional;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Entities.Profession;

@Repository
public interface ProfessionRepository extends CrudRepository<Profession, Long> {

	Optional<Profession> findByName(String profession_name);

	boolean existsByName(String profession_name);
	
	List<Profession> findByOrderByIdAsc();

}
