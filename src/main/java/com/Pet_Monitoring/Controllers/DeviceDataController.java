package com.Pet_Monitoring.Controllers;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Pet_Monitoring.Dto.DeviceDataDto;
import com.Pet_Monitoring.Entities.DeviceData;
import com.Pet_Monitoring.Services.DeviceDataService;

@RestController
@RequestMapping("/deviceData")
public class DeviceDataController {

	@Autowired
	DeviceDataService deviceDataService;

	@GetMapping
	public ResponseEntity<List<DeviceData>> read() {
		List<DeviceData> deviceData = deviceDataService.read();
		if (deviceData.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(deviceData);
	}

	@PostMapping(produces = "application/json")
	public ResponseEntity<DeviceData> create(@RequestBody @Validated DeviceDataDto deviceDataDto) {

		LocalDateTime localDateTime = LocalDateTime.now();
		Date date = java.util.Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

		DeviceData deviceData = new DeviceData();
		deviceData.setLatitude(deviceDataDto.getLatitude());
		deviceData.setLongitude(deviceDataDto.getLongitude());
		deviceData.setTemperature(deviceDataDto.getTemperature());
		deviceData.setBattery(deviceDataDto.getBattery());
		deviceData.setCreation_date(date);
		deviceData.setDevice(deviceDataDto.getDevice());
		deviceData.setPet(deviceDataDto.getPet());
		deviceDataService.create(deviceData);
		return ResponseEntity.ok(deviceData);
	}

}
