package com.Pet_Monitoring.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.Pet_Monitoring.Security.Entity.Usuarios;

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
	private int id;

	@NotNull
	private String code;

	private Date creation_date;

	private Date update_date;
	
	@OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
	private List<Device_Data> device_data;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Usuarios usuarios;

}
