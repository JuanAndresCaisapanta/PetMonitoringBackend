package com.Pet_Monitoring.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Pet_Monitoring.Dto.Message;
import com.Pet_Monitoring.Entities.Notification;
import com.Pet_Monitoring.Services.NotificationService;

@RestController
@RequestMapping("/notification")
@CrossOrigin
public class NotificationController {
	
	@Autowired
	NotificationService notificationService;
	
	@GetMapping("user/{userId}")
	public ResponseEntity<?> findAllByUsersId(@PathVariable("userId") Long userId) {
		List<Notification> notifications = notificationService.findAllByUsersId(userId);
		return new ResponseEntity<>(notifications, HttpStatus.OK);
	}

	@DeleteMapping("{notificationId}")
	public ResponseEntity<?> deleteNotification(@PathVariable("notificationId") Long notificationId) {
		notificationService.deleteNotification(notificationId);
		return new ResponseEntity<>(new Message("Notificación borrada"), HttpStatus.OK);

	}
}
