package com.btk.academia.rentACar.business.requests.carMaintanceRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarMaintanceRequest {
    private Integer id;
    private LocalDateTime maintenanceStart;
    private LocalDateTime maintenanceEnd;
    private Integer carId;
}
