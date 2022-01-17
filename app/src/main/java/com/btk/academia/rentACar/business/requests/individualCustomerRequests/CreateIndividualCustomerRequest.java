package com.btk.academia.rentACar.business.requests.individualCustomerRequests;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest {

	private Integer tc;
	
	private LocalDate birthDate;
	
	private String email;

}
