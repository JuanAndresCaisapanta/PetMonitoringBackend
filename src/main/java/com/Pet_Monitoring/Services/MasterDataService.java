package com.Pet_Monitoring.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Pet_Monitoring.Entities.MasterData;
import com.Pet_Monitoring.Repositories.MasterDataRepository;


@Service
@Transactional
public class MasterDataService {
	@Autowired
	MasterDataRepository masterDataRepository;

	public List<MasterData> read() {
		return (List<MasterData>) masterDataRepository.findAll();
	}

	public void create(MasterData masterData) {
		masterDataRepository.save(masterData);
	}
	
	public void delete(Long id) {
		masterDataRepository.deleteById(id);
	}
	
	public boolean existsById(Long id) {
		return masterDataRepository.existsById(id);
	}
}
