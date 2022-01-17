package com.btk.academia.rentACar.core.adapters;

import org.springframework.stereotype.Service;

@Service
public class KKBServiceAdapter implements CustomerCheckFindeksScore{
    @Override
    public Integer getIndividualCustomerFindeksScore(Integer tc) {
        int findeksScore = (int)(Math.random()*10+650)%1900;
        return findeksScore;
    }

    @Override
    public Integer getCorporateCustomerFindeksScore(Integer vergiNo) {
        int findeksScore = (int)(Math.random()*10+650)%1900;
        return findeksScore;
    }
}
