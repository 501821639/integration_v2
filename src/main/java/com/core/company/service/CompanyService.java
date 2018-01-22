package com.core.company.service;

import com.core.company.bean.Company;
import com.core.company.dto.CompanyUpdateMessage;
import com.core.user.dto.UserAllMessage;
import com.core.user.dto.UserAuthResult;

/**
 * Created by GSN on 2017/5/16.
 * 暂无描述
 */
public interface CompanyService {

    /**
     * 单位注册
     * 生成用户验证码
     *
     * @param key 用户账号
     * @param val 系统生成的验证码
     * @return 生成结果
     */
    UserAuthResult createCompanyAuth(String key, String val);

    /**
     * 单位注册
     *
     * @param company     输入的单位信息
     * @param username    用户输入的账号
     * @param userAuth    用户输入的验证码
     * @param sessionAuth 系统生成的验证码
     * @return 回执信息
     */
    String requiredRegisterCompany(Company company, String username, String userAuth, String sessionAuth);

    /**
     * 修改单位信息
     *
     * @param companyUpdateMessage 修的的单位信息
     * @param userAllMessage       当前操作的用户
     * @return 回执信息
     */
    String requiredUpdateComoany(CompanyUpdateMessage companyUpdateMessage, UserAllMessage userAllMessage);

    /**
     * 查询上级单位信息
     *
     * @param name 单位名称
     * @return json
     */
    String querySuperCompany(String name);

    /**
     * 查询用户可以维护的单位信息
     *
     * @param userAllMessage 用户
     * @return json
     */
    String queryUserCompany(UserAllMessage userAllMessage);


}
