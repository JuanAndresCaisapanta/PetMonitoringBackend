package com.Pet_Monitoring.Controllers;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Pet_Monitoring.Dto.DeviceDto;
import com.Pet_Monitoring.Dto.Message;
import com.Pet_Monitoring.Entities.Device;
import com.Pet_Monitoring.Services.DeviceService;
import com.Pet_Monitoring.Utils.Util;

@RestController
@RequestMapping("/device")
@CrossOrigin
public class DeviceController {

	@Autowired
	DeviceService deviceService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Device>> readAllDevice() {
		List<Device> devices = deviceService.readAllDevice();
		return new ResponseEntity<>(devices, HttpStatus.OK);
	}

	@GetMapping("/{device_id}")
	public ResponseEntity<Device> getByDeviceId(@PathVariable("device_id") Long device_id) {
		Device device = deviceService.getOneDevice(device_id).get();
		if (device == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(device);
	}

	@PostMapping
	public ResponseEntity<?> createDevice(@RequestBody @Validated DeviceDto deviceDto, BindingResult bindingResult) {
		Device device = new Device();
		if (bindingResult.hasErrors())
			return new ResponseEntity<>(new Message("Campos invalidos"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(deviceDto.getCode()))
			return new ResponseEntity<>(new Message("El código es necesario"), HttpStatus.BAD_REQUEST);
		if (deviceService.existsByDeviceCode(deviceDto.getCode()))
			return new ResponseEntity<>(new Message("El código no esta disponible"), HttpStatus.BAD_REQUEST);
		device.setCode(deviceDto.getCode());
		device.setCreation_date(Util.dateNow());
		device.setPet(deviceDto.getPet());
		device.setUsers(deviceDto.getUsers());
		deviceService.createDevice(device);
		return ResponseEntity.ok(device.getId());
	}

	@PutMapping("/{device_id}")
	public ResponseEntity<?> updateDevice(@PathVariable("device_id") Long device_id, @RequestBody DeviceDto deviceDto) {
		if (!deviceService.existsByDeviceId(device_id))
			return new ResponseEntity<>(new Message("El dispositivo no existe"), HttpStatus.NOT_FOUND);
		if (StringUtils.isBlank(deviceDto.getCallback_code()))
			return new ResponseEntity<>(new Message("El callback del dispostivo es obligatorio"),
					HttpStatus.BAD_REQUEST);
		Device device = deviceService.getOneDevice(device_id).get();
		device.setCallback_code(deviceDto.getCallback_code());
		deviceService.updateDevice(device);
		return new ResponseEntity<>(new Message("Dispositivo actualizado"), HttpStatus.OK);
	}

	@DeleteMapping("{device_id}")
	public ResponseEntity<?> deleteDevice(@PathVariable("device_id") Long device_id) {
		if (!deviceService.existsByDeviceId(device_id))
			return new ResponseEntity<>(new Message("El dispositivo no existe"), HttpStatus.NOT_FOUND);
		try {
			deviceService.deleteDevice(device_id);
			return new ResponseEntity<>(new Message("Dispositivo borrado"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new Message("Error al borrar el dispostivo"), HttpStatus.BAD_REQUEST);
		}
	}

}
