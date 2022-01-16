package com.btk.academia.rentACar.business.abstracts;

import com.btk.academia.rentACar.business.requests.userInfoRequests.CreateUserInfoRequest;
import com.btk.academia.rentACar.core.utilities.results.Result;

public interface UserInfoService {
    Result add(CreateUserInfoRequest userInfoRequest);
}
