package com.Pet_Monitoring.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Entities.DeviceData;

@Repository
public interface DeviceDataRepository extends CrudRepository<DeviceData, Integer>{
}
