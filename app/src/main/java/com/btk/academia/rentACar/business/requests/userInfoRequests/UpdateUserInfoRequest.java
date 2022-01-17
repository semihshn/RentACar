package com.btk.academia.rentACar.business.requests.userInfoRequests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserInfoRequest {
    private Integer id;
    private String name;
    private String cardNumber;
    private Integer customerId;
}
