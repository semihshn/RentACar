package com.btk.academia.rentACar.business.requests.carMaintanceRequest;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintanceRequest {
	
	private LocalDateTime maintenanceStart;
	private LocalDateTime maintenanceEnd;
	private Integer carId;

}
