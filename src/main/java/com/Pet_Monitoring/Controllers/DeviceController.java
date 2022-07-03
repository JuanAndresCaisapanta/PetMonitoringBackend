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
	public ResponseEntity<List<Device>> read() {
		List<Device> device = deviceService.readAll();
		if (device.isEmpty()) {
			return ResponseEntity.noContent().build();
		}   
		return ResponseEntity.ok(device);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Device> getById(@PathVariable("id") Long id) {
		Device device = deviceService.getOne(id).get();
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
	public ResponseEntity<?> create(@RequestBody @Validated DeviceDto deviceDto, BindingResult bindingResult) {

		Device device = new Device();
		if (bindingResult.hasErrors())
			return new ResponseEntity<>(new Message("Campos invalidos"), HttpStatus.BAD_REQUEST);

		if (StringUtils.isBlank(deviceDto.getCode()))
			return new ResponseEntity<>(new Message("El codigo es necesario"), HttpStatus.BAD_REQUEST);

		if (deviceService.existsByCode(deviceDto.getCode()))
			return new ResponseEntity<>(new Message("El código no esta disponible"), HttpStatus.BAD_REQUEST);
		
		device.setCode(deviceDto.getCode());
		device.setCreation_date(Util.dateNow());
		device.setPet(deviceDto.getPet());
		device.setUsers(deviceDto.getUsers());
		deviceService.create(device);
		return ResponseEntity.ok(device.getId());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody DeviceDto deviceDto) {
		if (!deviceService.existsById(id))
			return new ResponseEntity<>(new Message("El dispositivo no existe"), HttpStatus.NOT_FOUND);
		if (StringUtils.isBlank(deviceDto.getCallback_code()))
			return new ResponseEntity<>(new Message("El callback es obligatorio"), HttpStatus.BAD_REQUEST);
		Device device = deviceService.getOne(id).get();
		device.setCallback_code(deviceDto.getCallback_code());
		deviceService.update(device);
		return new ResponseEntity<>(new Message("Dispositivo actualizado"), HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		if (!deviceService.existsById(id))
			return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
		deviceService.delete(id);
		return new ResponseEntity<>(new Message("Dispositivo borrado"), HttpStatus.OK);
	}

}
