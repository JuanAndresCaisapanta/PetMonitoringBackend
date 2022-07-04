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

	@GetMapping
	public ResponseEntity<List<Device>> readAllDevice() {
		List<Device> device = deviceService.readAllDevice();
		if (device.isEmpty()) {
			return ResponseEntity.noContent().build();
		}   
		return ResponseEntity.ok(device);
	}

	@GetMapping("/{deviceId}")
	public ResponseEntity<Device> getByDeviceId(@PathVariable("deviceId") Long deviceId) {
		Device device = deviceService.getOneDevice(deviceId).get();
		if (device == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(device);
	}

	/*
	 * @RequestMapping(value = "/time/{id}", method = RequestMethod.GET, produces =
	 * "application/json") public ResponseEntity<?> getTimeById(@PathVariable("id")
	 * int id) {
	 * 
	 * Device device = deviceService.getOne(id).get(); return new
	 * ResponseEntity<>("{ \"407B49\":{ \"downlinkData\": \"000000000000000A\"} }",
	 * HttpStatus.OK);
	 * 
	 * }
	 */

	@PostMapping
	public ResponseEntity<?> createDevice(@RequestBody @Validated DeviceDto deviceDto, BindingResult bindingResult) {

		Device device = new Device();
		if (bindingResult.hasErrors())
			return new ResponseEntity<>(new Message("Campos invalidos"), HttpStatus.BAD_REQUEST);

		if (StringUtils.isBlank(deviceDto.getCode()))
			return new ResponseEntity<>(new Message("El codigo es necesario"), HttpStatus.BAD_REQUEST);

		if (deviceService.existsByDeviceCode(deviceDto.getCode()))
			return new ResponseEntity<>(new Message("El código no esta disponible"), HttpStatus.BAD_REQUEST);
		
		device.setCode(deviceDto.getCode());
		device.setCreation_date(Util.dateNow());
		device.setPet(deviceDto.getPet());
		device.setUsers(deviceDto.getUsers());
		deviceService.createDevice(device);
		return ResponseEntity.ok(device.getId());
	}
	
	@PutMapping("/{deviceId}")
	public ResponseEntity<?> updateDevice(@PathVariable("deviceId") Long deviceId, @RequestBody DeviceDto deviceDto) {
		if (!deviceService.existsByDeviceId(deviceId))
			return new ResponseEntity<>(new Message("El dispositivo no existe"), HttpStatus.NOT_FOUND);
		if (StringUtils.isBlank(deviceDto.getCallback_code()))
			return new ResponseEntity<>(new Message("El callback es obligatorio"), HttpStatus.BAD_REQUEST);
		Device device = deviceService.getOneDevice(deviceId).get();
		device.setCallback_code(deviceDto.getCallback_code());
		deviceService.updateDevice(device);
		return new ResponseEntity<>(new Message("Dispositivo actualizado"), HttpStatus.OK);
	}

	@DeleteMapping("{deviceId}")
	public ResponseEntity<?> deleteDevice(@PathVariable("deviceId") Long deviceId) {
		if (!deviceService.existsByDeviceId(deviceId))
			return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
		deviceService.deleteDevice(deviceId);
		return new ResponseEntity<>(new Message("Dispositivo borrado"), HttpStatus.OK);
	}

}
