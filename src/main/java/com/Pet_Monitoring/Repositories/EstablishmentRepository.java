package com.Pet_Monitoring.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Entities.Establishment;
import com.Pet_Monitoring.Entities.Pet;

@Repository
public interface EstablishmentRepository extends CrudRepository<Establishment, Integer> {

	@Query(value = "SELECT p FROM Pet p, Establishment e, Users u WHERE p.id=e.pet.id and p.users.id=u.id and e.name = :establishmentName and u.id = :userId")
	List<Pet> getPets(@Param("establishmentName") String name, @Param("userId") Long id);

}
