package com.Pet_Monitoring.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Pet_Monitoring.Dto.DeviceDetailDto;
import com.Pet_Monitoring.Dto.Message;
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
		return new ResponseEntity<>(deviceDetail, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/device/{device_id}")
	public ResponseEntity<List<DeviceDetail>> getByDeviceId(@PathVariable("device_id") Long device_id) {
		List<DeviceDetail> deviceDetails = deviceDetailService.findAllByDeviceId(device_id);
		return new ResponseEntity(deviceDetails, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(produces = "application/json")
	public ResponseEntity<?> createDeviceDetail(@RequestBody @Validated DeviceDetailDto deviceDetailDto) {
		DeviceDetail deviceDetail = new DeviceDetail();
		if (deviceDetailDto.getLatitude() == 0) {
			return new ResponseEntity(new Message("latitud no valida"), HttpStatus.BAD_REQUEST);
		}
		if (deviceDetailDto.getLongitude() == 0) {
			return new ResponseEntity(new Message("longitud no valida"), HttpStatus.BAD_REQUEST);
		}
		if (deviceDetailDto.getTemperature() == 0) {
			return new ResponseEntity(new Message("temperatura no valida"), HttpStatus.BAD_REQUEST);
		}
		if (deviceDetailDto.getBattery() == 0) {
			return new ResponseEntity(new Message("bateria no valida"), HttpStatus.BAD_REQUEST);
		}
		deviceDetail.setLatitude(deviceDetailDto.getLatitude());
		deviceDetail.setLongitude(deviceDetailDto.getLongitude());
		deviceDetail.setTemperature(deviceDetailDto.getTemperature());
		deviceDetail.setBattery(deviceDetailDto.getBattery());
		deviceDetail.setCreation_date(Util.dateNow());
		deviceDetail.setDevice(deviceDetailDto.getDevice());
		if (deviceDetailDto.getTemperature() > 39) {
			MessageDto user = deviceService.getUserDevice(deviceDetailDto.getDevice().getId());
			deviceService.sendEmailDevice("server.moniopet@gmail.com", user.getUser_email(), "Temperaura",
					"AVISO: La temperatura de su mascota" + user.getPet_name() + " esta alta y es de "
							+ deviceDetailDto.getTemperature() + " °C");
			Notification notification = new Notification();
			Users users = userService.getByUserId(user.getUser_id()).get();
			notification.setSubject("Temperatura");
			notification.setText("AVISO: La temperatura de su mascota" + user.getPet_name() + " esta alta y es de "
					+ deviceDetailDto.getTemperature() + " °C");
			notification.setUsers(users);
			notification.setCreation_date(Util.dateNow());
			notificationService.createNotification(notification);

		}
		if (deviceDetailDto.getTemperature() < 38) {
			MessageDto user = deviceService.getUserDevice(deviceDetailDto.getDevice().getId());
			deviceService.sendEmailDevice("server.moniopet@gmail.com", user.getUser_email(), "Temperaura",
					"AVISO: La temperatura de su mascota " + user.getPet_name() + " esta baja y es de "
							+ deviceDetailDto.getTemperature() + " °C");
			Notification notification = new Notification();
			Users users = userService.getByUserId(user.getUser_id()).get();
			notification.setSubject("Temperatura");
			notification.setText("AVISO: La temperatura de su mascota " + user.getPet_name() + " esta baja y es de "
					+ deviceDetailDto.getTemperature() + " °C");
			notification.setUsers(users);
			notification.setCreation_date(Util.dateNow());
			notificationService.createNotification(notification);

		}
		if (deviceDetailDto.getBattery() <= 2700) {
			MessageDto user = deviceService.getUserDevice(deviceDetailDto.getDevice().getId());
			deviceService.sendEmailDevice("server.moniopet@gmail.com", user.getUser_email(), "Bateria",
					"AVISO: La batería del dispositivo de su mascota " + user.getPet_name()
							+ " esta baja, cargue el dispostivo");
			Notification notification = new Notification();
			Users users = userService.getByUserId(user.getUser_id()).get();
			notification.setSubject("Batería");
			notification.setText("AVISO: La batería del dispositivo de su mascota " + user.getPet_name()
					+ " esta baja, cargue el dispostivo");
			notification.setUsers(users);
			notification.setCreation_date(Util.dateNow());
			notificationService.createNotification(notification);
		}
		deviceDetailService.createDeviceDetail(deviceDetail);
		return ResponseEntity.ok(deviceDetail);
	}
}
