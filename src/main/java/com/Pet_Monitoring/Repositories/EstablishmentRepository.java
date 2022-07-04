package com.Pet_Monitoring.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Dto.FullName;
import com.Pet_Monitoring.Entities.Establishment;
import com.Pet_Monitoring.Entities.Pet;

@Repository
public interface EstablishmentRepository extends CrudRepository<Establishment, Long> {

	@Query(value = "SELECT distinct p FROM Pet p, Establishment e, Users u WHERE e.pet.id=p.id and p.users.id=u.id and e.typeEstablishment.id = :typeEstablishmentId and e.name = :fullName and u.id = :userId")
	List<Pet> getEstablishmentPets(@Param("typeEstablishmentId") Long professionId, @Param("fullName") String fullName, @Param("userId") Long userId);

	@Query(value="select distinct e.name as fullName  from establishment e,pet p, users u  where e.pet_id =p.id and p.users_id =u.id  and p.users_id =:userId",nativeQuery = true)
	List<FullName> getEstablishmentFullNames(@Param("userId") Long userId);
	
}
