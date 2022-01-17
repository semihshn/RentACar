package com.btk.academia.rentACar.ws.controllers;

import com.btk.academia.rentACar.business.abstracts.PromoCodeService;
import com.btk.academia.rentACar.business.requests.promoCodeRequests.CreatePromoCodeRequest;
import com.btk.academia.rentACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/promationcodes")
public class PromoCodesController {

    private PromoCodeService promationCodeService;

    public PromoCodesController(PromoCodeService promationCodeService) {
        this.promationCodeService = promationCodeService;
    }

    @PostMapping("add")
    public Result add(@RequestBody CreatePromoCodeRequest createPromotionCodeRequest){
        return this.promationCodeService.add(createPromotionCodeRequest);
    }
}
