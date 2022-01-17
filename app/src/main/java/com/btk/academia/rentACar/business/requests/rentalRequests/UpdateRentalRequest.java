package com.btk.academia.rentACar.business.requests.rentalRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {
    private Integer id;
    private LocalDate rentDate;
    private LocalDate returnDate;
    private Integer rentedKilometer;
    private Integer returnedKilometer;
    private Integer customerId;
    private Integer carId;
    private Integer pickUpRentalCityId;
    private Integer returnRentalCityId;
}
