package com.Pet_Monitoring.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Entities.Device;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Integer>{
	boolean existsByCode(String code);
}
