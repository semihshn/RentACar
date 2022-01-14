package com.btk.academia.rentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

	private Double dailyPrice;
	private Integer modelYear;
	private String description;
	private Integer findexScore;
	private Integer kilometer;

}
