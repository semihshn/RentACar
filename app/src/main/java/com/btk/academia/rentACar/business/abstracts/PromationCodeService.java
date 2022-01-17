package com.btk.academia.rentACar.business.abstracts;

import com.btk.academia.rentACar.business.requests.promotionCodeRequests.CreatePromationCodeRequest;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.Result;
import com.btk.academia.rentACar.entities.concretes.PromationCode;

public interface PromationCodeService {
    Result add(CreatePromationCodeRequest createPromotionCodeRequest);
    DataResult<PromationCode> getByPromationCode(String code);
}
