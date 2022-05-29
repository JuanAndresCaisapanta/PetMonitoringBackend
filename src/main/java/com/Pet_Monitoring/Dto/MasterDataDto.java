package com.Pet_Monitoring.Dto;

import java.util.List;

import com.Pet_Monitoring.Entities.DetailData;
import com.Pet_Monitoring.Entities.Device;
import com.Pet_Monitoring.Entities.Pet;
import com.Pet_Monitoring.Security.Entities.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MasterDataDto {

	private Pet pet;

	private Users users;

	private Device device;

	private List<DetailData> detailData;
}
