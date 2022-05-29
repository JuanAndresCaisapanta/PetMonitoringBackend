package com.Pet_Monitoring.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.Pet_Monitoring.Security.Entities.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MasterData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private Users users;

	@ManyToOne
	@JoinColumn(name = "device_id")
	@JsonIgnore
	private Device device;

	@OneToMany(mappedBy = "masterData", cascade = CascadeType.ALL)
	private List<DetailData> detailData;
	
}
