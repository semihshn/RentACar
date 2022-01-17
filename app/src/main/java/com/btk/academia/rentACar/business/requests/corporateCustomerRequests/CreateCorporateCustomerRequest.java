package com.btk.academia.rentACar.business.requests.corporateCustomerRequests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorporateCustomerRequest {

	private Integer vergiNo;

	private String companyName;

}
