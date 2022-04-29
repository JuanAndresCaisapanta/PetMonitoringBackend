package com.Pet_Monitoring.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Entity.Device;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Integer>{
}
