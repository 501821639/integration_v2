package com.core.user.dao;

import com.core.user.bean.UserCompany;

import java.util.List;

/**
 * Created by GSN on 2017/5/11.
 * 暂无描述
 */


public interface UserCompanyDao {

    void addUserCompany(UserCompany userCompany);

    void deleteUserCompany(UserCompany userCompany);

    void deleteUserCompanys(List<UserCompany> list);

    void updateUserCompany(UserCompany userCompany);

    UserCompany getUserCompany(String id);

    List<?> queryUserCompany(String hql);

}
