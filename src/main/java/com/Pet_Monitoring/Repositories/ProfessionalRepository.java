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

	@Query(value = "SELECT pr FROM Professional pr, Pet p, Users u WHERE pr.pet.id = p.id and p.users.id =u.id and u.status =true ORDER BY pr.id  asc")
	List<Professional> findProfessionalByOrderByIdAsc();
	
	@Query(value = "SELECT distinct p FROM Professional pr, Pet p, Users u WHERE pr.pet.id=p.id and p.users.id=u.id and pr.profession.id = :profession_id and concat( pr.name,' ', pr.last_name) = :professional_fullName and u.id = :user_id")
	List<Pet> getProfessionalPets(@Param("profession_id") Long profession_id,
			@Param("professional_fullName") String professional_fullName, @Param("user_id") Long user_id);

	@Query(value = "select distinct concat(pr.name,' ',pr.last_name) as full_name  from professional pr,pet p, users u  where pr.pet_id =p.id and p.users_id =u.id  and p.users_id =:user_id", nativeQuery = true)
	List<FullName> getProfessionalFullNames(@Param("user_id") Long user_id);

}
