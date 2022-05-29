package com.Pet_Monitoring.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Entities.DetailData;

@Repository
public interface DetailDataRepository extends CrudRepository<DetailData, Long>{
}
