package com.btk.academia.rentACar.business.concretes;

import com.btk.academia.rentACar.business.abstracts.PromoCodeService;
import com.btk.academia.rentACar.business.requests.promoCodeRequests.CreatePromoCodeRequest;
import com.btk.academia.rentACar.core.utilities.business.BusinessRules;
import com.btk.academia.rentACar.core.utilities.mapping.ModelMapperService;
import com.btk.academia.rentACar.core.utilities.results.*;
import com.btk.academia.rentACar.dataAccess.abstracts.PromoCodeDao;
import com.btk.academia.rentACar.entities.concretes.PromoCode;
import org.springframework.stereotype.Service;

@Service
public class PromoCodeManager implements PromoCodeService {

    private PromoCodeDao promotionCodeDao;
    private ModelMapperService modelMapperService;

    public PromoCodeManager(PromoCodeDao promotionCodeDao, ModelMapperService modelMapperService) {
        this.promotionCodeDao = promotionCodeDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreatePromoCodeRequest createPromotionCodeRequest) {
        Result result = BusinessRules.run(
                checkIfPromationCodeExistsByCode(createPromotionCodeRequest.getPromationCode())
        );

        if(!result.isSuccess()) {
            return result;
        }

        PromoCode promotionCode = modelMapperService.forRequest().map(createPromotionCodeRequest, PromoCode.class);

        this.promotionCodeDao.save(promotionCode);
        return new SuccessResult("indirim kodu eklendi");
    }

    @Override
    public DataResult<PromoCode> getByPromationCode(String code) {
        PromoCode promationCode = this.promotionCodeDao.findByPromationCode(code);

        return new SuccessDataResult<PromoCode>(promationCode);
    }

    private Result checkIfPromationCodeExistsByCode(String code){
        if(promotionCodeDao.findByPromationCode(code)!=null) {
            return new ErrorResult("Bu promosyon kodu daha önce tanımlanmıştır");
        }
        else return new SuccessResult();
    }
}
