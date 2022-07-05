package com.Pet_Monitoring.Entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.Pet_Monitoring.Security.Entities.Users;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	private String code;
	
	private String callback_code;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date creation_date;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date update_date;
	
	@ManyToOne
	@JoinColumn(name = "pet_id")
	@JsonIgnoreProperties({"establishment","medicine","professional","device","image"})
	private Pet pet;

	@ManyToOne
	@JoinColumn(name = "users_id")
	@JsonIgnore
	private Users users;

	@OneToMany(mappedBy = "device",  cascade =  CascadeType.REMOVE, orphanRemoval = true)
	private List<DeviceDetail> deviceDetail;
	
}
