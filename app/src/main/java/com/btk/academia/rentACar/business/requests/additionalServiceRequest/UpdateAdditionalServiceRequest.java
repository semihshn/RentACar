package com.btk.academia.rentACar.business.requests.additionalServiceRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdditionalServiceRequest {
    private Integer id;
    private String name;
    private Double price;
    private Integer rentalId;
}
