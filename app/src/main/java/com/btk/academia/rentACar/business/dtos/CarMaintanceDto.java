package com.btk.academia.rentACar.business.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarMaintanceDto {
	
	private Integer carId;
	private LocalDateTime maintenanceStart;
	private LocalDateTime maintenanceEnd;

}
