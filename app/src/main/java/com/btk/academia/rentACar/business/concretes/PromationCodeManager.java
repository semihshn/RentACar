package com.btk.academia.rentACar.business.concretes;

import com.btk.academia.rentACar.business.abstracts.PromationCodeService;
import com.btk.academia.rentACar.business.requests.promotionCodeRequests.CreatePromationCodeRequest;
import com.btk.academia.rentACar.core.utilities.business.BusinessRules;
import com.btk.academia.rentACar.core.utilities.mapping.ModelMapperService;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.Result;
import com.btk.academia.rentACar.core.utilities.results.SuccessDataResult;
import com.btk.academia.rentACar.core.utilities.results.SuccessResult;
import com.btk.academia.rentACar.dataAccess.abstracts.PromationCodeDao;
import com.btk.academia.rentACar.entities.concretes.PromationCode;
import org.springframework.stereotype.Service;

@Service
public class PromationCodeManager implements PromationCodeService {

    private PromationCodeDao promotionCodeDao;
    private ModelMapperService modelMapperService;

    public PromationCodeManager(PromationCodeDao promotionCodeDao, ModelMapperService modelMapperService) {
        this.promotionCodeDao = promotionCodeDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreatePromationCodeRequest createPromotionCodeRequest) {
        Result result = BusinessRules.run(

        );

        if(!result.isSuccess()) {
            return result;
        }

        PromationCode promotionCode = modelMapperService.forRequest().map(createPromotionCodeRequest, PromationCode.class);

        this.promotionCodeDao.save(promotionCode);
        return new SuccessResult("indirim kodu eklendi");
    }

    @Override
    public DataResult<PromationCode> getByPromationCode(String code) {
        PromationCode promationCode = this.promotionCodeDao.findByPromationCode(code);

        return new SuccessDataResult<PromationCode>(promationCode);
    }
}
