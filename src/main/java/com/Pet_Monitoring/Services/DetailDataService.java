package com.Pet_Monitoring.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Pet_Monitoring.Entities.DetailData;
import com.Pet_Monitoring.Repositories.DetailDataRepository;

@Service
@Transactional
public class DetailDataService {

	@Autowired
	DetailDataRepository detailDataRepository;

	public List<DetailData> read() {
		return (List<DetailData>) detailDataRepository.findAll();
	}

	public void create(DetailData detailData) {
		detailDataRepository.save(detailData);
	}

}
