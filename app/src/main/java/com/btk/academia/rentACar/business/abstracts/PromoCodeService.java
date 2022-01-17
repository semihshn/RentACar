package com.btk.academia.rentACar.business.abstracts;

import com.btk.academia.rentACar.business.requests.promoCodeRequests.CreatePromoCodeRequest;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.Result;
import com.btk.academia.rentACar.entities.concretes.PromoCode;

public interface PromoCodeService {
    Result add(CreatePromoCodeRequest createPromotionCodeRequest);
    DataResult<PromoCode> getByPromationCode(String code);
}
