package com.btk.academia.rentACar.business.requests.promotionCodeRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePromationCodeRequest {

    private String promationCode;
    private Double discountRate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
