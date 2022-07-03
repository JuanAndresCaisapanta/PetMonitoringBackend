package com.Pet_Monitoring.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Pet_Monitoring.Entities.DeviceDetail;
import com.Pet_Monitoring.Repositories.DeviceDetailRepository;

@Service
@Transactional
public class DeviceDetailService {

	@Autowired
	DeviceDetailRepository deviceDetailRepository;

	public List<DeviceDetail> read() {
		return (List<DeviceDetail>) deviceDetailRepository.findAll();
	}

	public void create(DeviceDetail deviceDetail) {
		deviceDetailRepository.save(deviceDetail);
	}

}
