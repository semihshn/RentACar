package com.btk.academia.rentACar.core.adapters;

import com.btk.academia.rentACar.business.requests.userInfoRequests.CreateUserInfoRequest;
import com.btk.academia.rentACar.core.utilities.results.Result;

public interface CustomerCheckLimitService {

    public Result checkIfLimitIsEnought();
}
