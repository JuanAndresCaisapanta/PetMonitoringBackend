package com.Pet_Monitoring.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Entities.Notification;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {

	List<Notification> findAllByUsersId(Long id);

}
