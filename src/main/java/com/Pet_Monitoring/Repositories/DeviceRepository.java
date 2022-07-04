package com.Pet_Monitoring.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Dto.MessageDto;
import com.Pet_Monitoring.Entities.Device;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {
	
	@Query(value = "select\r\n"
			+ "	u.id as idUser,\r\n"
			+ "	u.email as emailUser,\r\n"
			+ "	p.name as petName\r\n"
			+ "from\r\n"
			+ "	users u,\r\n"
			+ "	pet p ,\r\n"
			+ "	device d\r\n"
			+ "where\r\n"
			+ "	u.id = d.user_id\r\n"
			+ "	and d.pet_id = p.id\r\n"
			+ "	and d.id = :deviceId", nativeQuery = true)
	MessageDto getUserDevice(@Param("deviceId") Long deviceId);
	
	boolean existsByCode(String deviceCode);

}
