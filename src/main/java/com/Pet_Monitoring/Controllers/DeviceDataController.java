package com.Pet_Monitoring.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Pet_Monitoring.Dto.DetailDataDto;
import com.Pet_Monitoring.Entities.DetailData;
import com.Pet_Monitoring.Services.DetailDataService;
import com.Pet_Monitoring.Utils.Util;

@RestController
@RequestMapping("/deviceData")
public class DeviceDataController {

	@Autowired
	DetailDataService detailDataService;

	@GetMapping
	public ResponseEntity<List<DetailData>> read() {
		
		List<DetailData> detailData = detailDataService.read();
		if (detailData.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(detailData);
	}

	@PostMapping
	public ResponseEntity<DetailData> create(@RequestBody @Validated DetailDataDto detailDataDto) {

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
