package com.Pet_Monitoring.Dto;

import java.util.List;

import com.Pet_Monitoring.Entities.Medicine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeMedicineDto {

	private String name;

	private List<Medicine> medicine;
}
