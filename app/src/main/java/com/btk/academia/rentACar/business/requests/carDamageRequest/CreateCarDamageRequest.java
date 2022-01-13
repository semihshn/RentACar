package com.btk.academia.rentACar.business.requests.carDamageRequest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarDamageRequest {
	private Integer carId;
	private String description;


}
