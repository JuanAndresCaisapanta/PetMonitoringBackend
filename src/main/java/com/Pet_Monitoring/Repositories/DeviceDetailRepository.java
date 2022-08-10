package com.Pet_Monitoring.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Entities.DeviceDetail;

@Repository
public interface DeviceDetailRepository extends CrudRepository<DeviceDetail, Long>{
	
	List<DeviceDetail> findAllByDeviceId(Long device_id);
}
