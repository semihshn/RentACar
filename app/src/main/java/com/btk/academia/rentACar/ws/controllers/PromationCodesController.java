package com.btk.academia.rentACar.ws.controllers;

import com.btk.academia.rentACar.business.abstracts.PromationCodeService;
import com.btk.academia.rentACar.business.requests.promotionCodeRequests.CreatePromationCodeRequest;
import com.btk.academia.rentACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/promationcodes")
public class PromationCodesController {

    private PromationCodeService promationCodeService;

    public PromationCodesController(PromationCodeService promationCodeService) {
        this.promationCodeService = promationCodeService;
    }

    @PostMapping("add")
    public Result add(@RequestBody CreatePromationCodeRequest createPromotionCodeRequest){
        return this.promationCodeService.add(createPromotionCodeRequest);
    }
}
