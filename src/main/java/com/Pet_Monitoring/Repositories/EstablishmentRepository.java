package com.Pet_Monitoring.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Dto.FullName;
import com.Pet_Monitoring.Entities.Establishment;
import com.Pet_Monitoring.Entities.Medicine;
import com.Pet_Monitoring.Entities.Pet;

@Repository
public interface EstablishmentRepository extends CrudRepository<Establishment, Long> {
	
	List<Establishment> findByOrderByIdAsc();

	@Query(value = "SELECT distinct p FROM Pet p, Establishment e, Users u WHERE e.pet.id=p.id and p.users.id=u.id and e.establishmentType.id = :establishmentType_id and e.name = :establishment_fullName and u.id = :user_id")
	List<Pet> getEstablishmentPets(@Param("establishmentType_id") Long establishmentType_id,
			@Param("establishment_fullName") String establishment_fullName, @Param("user_id") Long user_id);

	@Query(value = "select distinct e.name as full_name  from establishment e,pet p, users u  where e.pet_id =p.id and p.users_id =u.id  and p.users_id =:user_id", nativeQuery = true)
	List<FullName> getEstablishmentFullNames(@Param("user_id") Long user_id);

}
