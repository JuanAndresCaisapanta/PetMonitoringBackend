package com.Pet_Monitoring.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Pet_Monitoring.Entity.Device;
import com.Pet_Monitoring.Repository.DeviceRepository;

@Service
@Transactional
public class DispositivosService {
	
	@Autowired
	DeviceRepository dispositivosRepository;

	public List<Device> lista() {
		return (List<Device>) dispositivosRepository.findAll();
	}

	public Optional<Device> getOne(int id) {
		return dispositivosRepository.findById(id);
	}



	public void guardar(Device dispositivos) {
		dispositivosRepository.save(dispositivos);
	}

	public void actualizar(Device dispositivos) {
		dispositivosRepository.save(dispositivos);
	}

	public void eliminar(int id) {
		dispositivosRepository.deleteById(id);
	}

	public boolean existsById(int id) {
		return dispositivosRepository.existsById(id);
	}

}
