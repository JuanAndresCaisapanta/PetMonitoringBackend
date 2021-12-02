package com.MonitoreoMascotas.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.MonitoreoMascotas.Entity.Recopilaciones;

@Repository
public interface RecopilacionesRepository extends JpaRepository<Recopilaciones, Integer>{
}
