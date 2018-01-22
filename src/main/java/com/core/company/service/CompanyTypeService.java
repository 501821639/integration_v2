package com.core.company.service;

import com.core.company.bean.CompanyType;

/**
 * Created by GSN on 2017/5/16.
 * 暂无描述
 */
public interface CompanyTypeService {

    /**
     * 添加单位类型
     *
     * @param companyType 单位类型实体类
     * @return 回执信息
     */
    String requiredAddCompanyType(CompanyType companyType);

    /**
     * 删除单位类型
     * 敏感操作后期使用会尽量不要开启删除权限
     * 单位使用不能删除
     *
     * @param id 单位类型id
     * @return 回执信息
     */
    String requiredDeleteCompanyType(String id);

    /**
     * 修改单位类型
     *
     * @param companyType 单位类型实体类
     * @return 回执信息
     */
    String requiredUpdateCompanyType(CompanyType companyType);

    /**
     * 查询所有单位类型
     *
     * @return json格式数据
     */
    String queryCompanyType();

}
