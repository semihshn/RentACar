package com.btk.academia.rentACar.business.requests.rentalRequests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {
	
	private LocalDate rentDate;
	
	private LocalDate returnDate;
	
	private Integer rentedKilometer;
	
	private Integer returnedKilometer;
	
	private Integer customerId;
	private Integer carId;
	private Integer pickUpRentalCityId;
	private Integer returnRentalCityId;

}
