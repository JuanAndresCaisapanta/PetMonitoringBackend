package com.Pet_Monitoring.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Pet_Monitoring.Entities.Device;
import com.Pet_Monitoring.Repositories.DeviceRepository;

@Service
@Transactional
public class DeviceService {

	@Autowired
	DeviceRepository deviceRepository;

	public List<Device> read() {
		return (List<Device>) deviceRepository.findAll();
	}

	public void create(Device device) {
		deviceRepository.save(device);
	}

	public void update(Device device) {
		deviceRepository.save(device);
	}

	public void delete(int id) {
		deviceRepository.deleteById(id);
	}

	public Optional<Device> getOne(int id) {
		return deviceRepository.findById(id);
	}

	public boolean existsById(int id) {
		return deviceRepository.existsById(id);
	}

}
