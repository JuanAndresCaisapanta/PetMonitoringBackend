package com.Pet_Monitoring.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Pet_Monitoring.Entities.DeviceData;
import com.Pet_Monitoring.Repositories.DeviceDataRepository;

@Service
@Transactional
public class DeviceDataService {

	@Autowired
	DeviceDataRepository deviceDataRepository;

	public List<DeviceData> read() {
		return (List<DeviceData>) deviceDataRepository.findAll();
	}

	public void create(DeviceData deviceData) {
		deviceDataRepository.save(deviceData);
	}

}
