package com.Pet_Monitoring.Controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Pet_Monitoring.Dto.DeviceDetailDto;
import com.Pet_Monitoring.Dto.MessageDto;
import com.Pet_Monitoring.Entities.DeviceDetail;
import com.Pet_Monitoring.Entities.Notification;
import com.Pet_Monitoring.Security.Entities.Users;
import com.Pet_Monitoring.Security.Services.UserService;
import com.Pet_Monitoring.Services.DeviceDetailService;
import com.Pet_Monitoring.Services.DeviceService;
import com.Pet_Monitoring.Services.NotificationService;
import com.Pet_Monitoring.Utils.Util;

@RestController
@RequestMapping("/device-detail")
@CrossOrigin
@Transactional
public class DeviceDetailController {

	@Autowired
	DeviceService deviceService;
	@Autowired
	DeviceDetailService deviceDetailService;
	@Autowired
	NotificationService notificationService;
	@Autowired
	UserService userService;

	@GetMapping
	public ResponseEntity<List<DeviceDetail>> readAllDeviceDetail() {

		List<DeviceDetail> deviceDetail = deviceDetailService.readAllDeviceDetail();
		if (deviceDetail.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(deviceDetail);
	}

	@PostMapping(produces = "application/json")
	public ResponseEntity<?> createDeviceDetail(@RequestBody @Validated DeviceDetailDto deviceDetailDto) {
		DeviceDetail deviceDetail = new DeviceDetail();
		deviceDetail.setLatitude(deviceDetailDto.getLatitude());
		deviceDetail.setLongitude(deviceDetailDto.getLongitude());
		deviceDetail.setTemperature(deviceDetailDto.getTemperature());
		deviceDetail.setBattery(deviceDetailDto.getBattery());
		deviceDetail.setCreation_date(Util.dateNow());
		deviceDetail.setDevice(deviceDetailDto.getDevice());
		if (deviceDetailDto.getTemperature() <= 17) {
			MessageDto user = deviceService.getUserDevice(deviceDetailDto.getDevice().getId());
			deviceService.sendEmailDevice("server.moniopet@gmail.com", user.getEmailUser(), "Temperaura",
					"la temperatura de " + user.getPetName() + " esta baja");
			Notification notification = new Notification();
			Users users = userService.getByUserId(user.getIdUser()).get();
			notification.setSubject("Temperatura");
			notification.setText("la temperatura de " + user.getPetName() + " esta baja");
			notification.setUsers(users);
			notificationService.createNotification(notification);

		}
		if (deviceDetailDto.getBattery() <= 17) {
			MessageDto user = deviceService.getUserDevice(deviceDetailDto.getDevice().getId());
			deviceService.sendEmailDevice("server.moniopet@gmail.com", user.getEmailUser(), "Bateria",
					"la bateria del dispositivo de " + user.getPetName() + " esta baja");
			Notification notification = new Notification();
			Users users = userService.getByUserId(user.getIdUser()).get();
			notification.setSubject("Bateria");
			notification.setText("la bateria del dispositivo de " + user.getPetName() + " esta baja");
			notification.setUsers(users);
			notificationService.createNotification(notification);
		}
		deviceDetailService.createDeviceDetail(deviceDetail);
		return ResponseEntity.ok(deviceDetail);

	}

}
