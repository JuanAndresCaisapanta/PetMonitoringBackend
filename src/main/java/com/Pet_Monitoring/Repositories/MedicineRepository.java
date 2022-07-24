package com.Pet_Monitoring.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Dto.FullName;
import com.Pet_Monitoring.Entities.Device;
import com.Pet_Monitoring.Entities.Medicine;
import com.Pet_Monitoring.Entities.Pet;

@Repository
public interface MedicineRepository extends CrudRepository<Medicine, Long>{
	
	List<Medicine> findAllByPetId(Long pet_id);
	
	@Query(value = "SELECT distinct p FROM Pet p, Medicine m, Users u WHERE m.pet.id=p.id and p.users.id=u.id and m.medicineType.id = :medicineType_id and m.name = :medicine_fullName and u.id = :user_id")
	List<Pet> getMedicinePets(@Param("medicineType_id") Long medicineType_id,
			@Param("medicine_fullName") String medicine_fullName, @Param("user_id") Long user_id);

	@Query(value = "select distinct m.name as full_name  from medicine m,pet p, users u  where m.pet_id =p.id and p.users_id =u.id  and p.users_id =:user_id", nativeQuery = true)
	List<FullName> getMedicineFullNames(@Param("user_id") Long user_id);

	List<Medicine> findByOrderByIdAsc();
}
