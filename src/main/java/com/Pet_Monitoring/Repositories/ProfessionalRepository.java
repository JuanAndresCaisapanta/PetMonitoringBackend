package com.Pet_Monitoring.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Dto.FullName;
import com.Pet_Monitoring.Entities.Pet;
import com.Pet_Monitoring.Entities.Professional;

@Repository
public interface ProfessionalRepository extends CrudRepository<Professional, Long> {

	List<Professional> findAllByPetId(Long petId);
	
	@Query(value = "SELECT distinct p FROM Professional pr, Pet p, Users u WHERE pr.pet.id=p.id and p.users.id=u.id and pr.profession.id = :professionId and concat( pr.name,' ', pr.last_name) = :fullName and u.id = :userId")
	List<Pet> getProfessionalPets(@Param("professionId") Long professionId, @Param("fullName") String fullName, @Param("userId") Long userId);
	
	@Query(value="select distinct concat(pr.name,' ',pr.last_name) as fullName  from professional pr,pet p, users u  where pr.pet_id =p.id and p.users_id =u.id  and p.users_id =:userId",nativeQuery = true)
	List<FullName> getProfessionalFullNames(@Param("userId") Long userId);
	
}
