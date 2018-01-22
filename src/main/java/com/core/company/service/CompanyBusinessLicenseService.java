package com.core.company.service;

import com.core.company.bean.CompanyBusinessLicense;
import com.core.user.dto.UserAllMessage;

/**
 * Created by GSN on 2017/5/16.
 * 暂无描述
 */
public interface CompanyBusinessLicenseService {

    /**
     * 添加单位凭证
     *
     * @param companyBusinessLicense 实体类
     * @param userAllMessage         授权用户
     * @return 回执信息
     */
    String requiredAddCompanyBusinessLicense(CompanyBusinessLicense companyBusinessLicense, UserAllMessage userAllMessage);

    /**
     * 删除单位明证
     * 验证：只能删除自己的单位凭证
     *
     * @param id             凭证id
     * @param userAllMessage 授权用户
     * @return 回执信息
     */
    String requiredDeleteCompanyBusinessLicense(String id, UserAllMessage userAllMessage);

    /**
     * 查询单位凭证
     *
     * @param companyId      单位id
     * @param userAllMessage 授权用户
     * @return json数据
     */
    String queryCompanyBusinessLicense(String companyId, UserAllMessage userAllMessage);

}
