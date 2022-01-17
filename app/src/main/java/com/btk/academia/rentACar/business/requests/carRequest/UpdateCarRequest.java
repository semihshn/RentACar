package com.btk.academia.rentACar.business.requests.carRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {
	
	private Integer id;
	
	private Double dailyPrice;
	
	private Integer modelYear;
	
	private String description;
	
	private Integer findexScore;
	
	private Integer kilometer;

	private Integer minAge;

}
