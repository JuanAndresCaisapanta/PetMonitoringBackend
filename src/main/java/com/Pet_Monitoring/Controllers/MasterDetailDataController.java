package com.Pet_Monitoring.Controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Pet_Monitoring.Dto.DetailDataDto;
import com.Pet_Monitoring.Dto.MasterDataDto;
import com.Pet_Monitoring.Dto.Message;
import com.Pet_Monitoring.Entities.DetailData;
import com.Pet_Monitoring.Entities.MasterData;
import com.Pet_Monitoring.Services.DetailDataService;
import com.Pet_Monitoring.Services.MasterDataService;
import com.Pet_Monitoring.Utils.Util;

@RestController
@RequestMapping("/master-detail-data")
@CrossOrigin
@Transactional
public class MasterDetailDataController {

	@Autowired
	MasterDataService masterDataService;
	@Autowired
	DetailDataService detailDataService;
	

	/*
	 * @GetMapping public ResponseEntity<List<DetailData>> read() {
	 * 
	 * List<DetailData> detailData = detailDataService.read(); if
	 * (detailData.isEmpty()) { return ResponseEntity.noContent().build(); } return
	 * ResponseEntity.ok(detailData); }
	 */
	
	@PostMapping("/master")
	public ResponseEntity<?> createMaster(@RequestBody @Validated MasterDataDto masterDataDto) {

		MasterData masterData = new MasterData();
		masterData.setPet(masterDataDto.getPet());
		masterData.setDevice(masterDataDto.getDevice());
		masterData.setUsers(masterDataDto.getUsers());
		masterDataService.create(masterData);
		return ResponseEntity.ok(masterData.getId());
		
	}
	
	@DeleteMapping("master/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {

		if (!masterDataService.existsById(id))
			return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
		masterDataService.delete(id);
		return new ResponseEntity<>(new Message("Master borrado"), HttpStatus.OK);

	}

	@PostMapping("/detail")
	public ResponseEntity<?> createDetail(@RequestBody @Validated DetailDataDto detailDataDto) {
		DetailData detailData = new DetailData();
		detailData.setLatitude(detailDataDto.getLatitude());
		detailData.setLongitude(detailDataDto.getLongitude());
		detailData.setTemperature(detailDataDto.getTemperature());
		detailData.setBattery(detailDataDto.getBattery());
		detailData.setCreation_date(Util.dateNow());
		detailData.setMasterData(detailDataDto.getMasterData());
		detailDataService.create(detailData);
		return ResponseEntity.ok(detailData);
		
	}
	
}
