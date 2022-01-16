package com.btk.academia.rentACar.core.adapters;

import com.btk.academia.rentACar.business.requests.userInfoRequests.CreateUserInfoRequest;
import com.btk.academia.rentACar.core.utilities.results.Result;
import com.btk.academia.rentACar.core.utilities.results.SuccessResult;
import org.springframework.stereotype.Service;

@Service
public class IsBankServiceAdapter implements CustomerCheckLimitService {
    @Override
    public Result checkIfLimitIsEnought() {
        return new SuccessResult();
    }
}
