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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Float latitude;
	
	@NotNull
	private Float longitude;
	
	@NotNull
	private Float temperature;
	
	@NotNull
	private int battery;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date creation_date;

	@ManyToOne
	@JoinColumn(name = "device_id")
	@JsonIgnoreProperties({"deviceDetail"})
	private Device device;

}
