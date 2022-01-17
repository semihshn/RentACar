package com.btk.academia.rentACar.business.requests.promoCodeRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePromoCodeRequest {
    private Integer id;
    private String promationCode;
    private Double discountRate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
