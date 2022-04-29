package com.Pet_Monitoring.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Pet_Monitoring.Entity.Device_Data;
import com.Pet_Monitoring.Repository.Device_DataRepository;

@Service
@Transactional
public class RecopilacionesService {

	@Autowired
	Device_DataRepository recopilacionesRepository;

	public List<Device_Data> lista() {
		return (List<Device_Data>) recopilacionesRepository.findAll();
	}

	public void guardar(Device_Data recopilaciones) {
		recopilacionesRepository.save(recopilaciones);
	}

}
