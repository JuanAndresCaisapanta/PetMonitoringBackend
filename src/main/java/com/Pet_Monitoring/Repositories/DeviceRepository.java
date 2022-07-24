package com.Pet_Monitoring.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Dto.MessageDto;
import com.Pet_Monitoring.Entities.Device;
import com.Pet_Monitoring.Entities.Pet;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {
	
	@Query(value = "select\r\n"
			+ "	u.id as user_id,\r\n"
			+ "	u.email as user_email,\r\n"
			+ "	p.name as pet_name\r\n"
			+ "from\r\n"
			+ "	users u,\r\n"
			+ "	pet p ,\r\n"
			+ "	device d\r\n"
			+ "where\r\n"
			+ "	u.id = d.users_id\r\n"
			+ "	and d.pet_id = p.id\r\n"
			+ "	and d.id = :device_id", nativeQuery = true)
	MessageDto getUserDevice(@Param("device_id") Long device_id);
	
	boolean existsByCode(String device_code);

	List<Device> findByOrderByIdAsc();
}
