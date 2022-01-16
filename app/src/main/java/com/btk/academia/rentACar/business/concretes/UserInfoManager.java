package com.btk.academia.rentACar.business.concretes;

import com.btk.academia.rentACar.business.abstracts.UserInfoService;
import com.btk.academia.rentACar.business.requests.userInfoRequests.CreateUserInfoRequest;
import com.btk.academia.rentACar.core.utilities.business.BusinessRules;
import com.btk.academia.rentACar.core.utilities.mapping.ModelMapperService;
import com.btk.academia.rentACar.core.utilities.results.Result;
import com.btk.academia.rentACar.core.utilities.results.SuccessResult;
import com.btk.academia.rentACar.dataAccess.abstracts.UserInfoDao;
import com.btk.academia.rentACar.entities.concretes.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoManager implements UserInfoService {

    private UserInfoDao userInfoDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public UserInfoManager(UserInfoDao userInfoDao,ModelMapperService modelMapperService) {
        this.userInfoDao=userInfoDao;
        this.modelMapperService=modelMapperService;
    }

    @Override
    public Result add(CreateUserInfoRequest userInfoRequest) {
        Result result = BusinessRules.run(

        );

        if(!result.isSuccess()) {
            return result;
        }

        UserInfo userInfo = modelMapperService.forRequest().map(userInfoRequest, UserInfo.class);
        userInfo.setId(0);
        this.userInfoDao.save(userInfo);
        return new SuccessResult("Kullanıcı bilgileri eklendi");
    }
}
