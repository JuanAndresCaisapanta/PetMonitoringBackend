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
@CrossOrigin(origins = {"http://localhost:8080","https://moniopet.vercel.app"})
public class NotificationController {
	
	@Autowired
	NotificationService notificationService;
	
	@GetMapping("user/{user_id}")
	public ResponseEntity<?> findAllByUsersId(@PathVariable("user_id") Long user_id) {
		List<Notification> notifications = notificationService.findAllByUsersId(user_id);
		return new ResponseEntity<>(notifications, HttpStatus.OK);
	}

	@DeleteMapping("{notification_id}")
	public ResponseEntity<?> deleteNotification(@PathVariable("notification_id") Long notification_id) {
		notificationService.deleteNotification(notification_id);
		return new ResponseEntity<>(new Message("Notificación borrada"), HttpStatus.OK);
	}
	
}
