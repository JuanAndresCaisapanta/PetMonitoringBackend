package com.Pet_Monitoring.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Entity.Device_Data;

@Repository
public interface RecopilacionesRepository extends JpaRepository<Device_Data, Integer>{
}
