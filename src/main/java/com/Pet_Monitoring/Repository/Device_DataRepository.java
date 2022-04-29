package com.Pet_Monitoring.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Entity.Device_Data;

@Repository
public interface Device_DataRepository extends CrudRepository<Device_Data, Integer>{
}
