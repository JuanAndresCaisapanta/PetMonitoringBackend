package com.Pet_Monitoring.Entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	private Float latitude;
	
	@NotNull
	private Float longitude;
	
	@NotNull
	private Float temperature;
	
	@NotNull
	private int battery;
	
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date creation_date;

	@ManyToOne
	@JoinColumn(name = "device_id")
	private Device device;

	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;

}
