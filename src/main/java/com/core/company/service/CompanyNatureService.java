package com.core.company.service;

import com.core.company.bean.CompanyNature;

/**
 * Created by GSN on 2017/5/16.
 * 暂无描述
 */
public interface CompanyNatureService {

    /**
     * 添加单位性质
     *
     * @param companyNature 单位性质实体类
     * @return 回执信息
     */
    String requiredAddCompanyNature(CompanyNature companyNature);

    /**
     * 删除单位性质
     * 敏感操作后期使用会尽量不要开启删除权限
     * 单位使用不能删除
     *
     * @param id 单位性质id
     * @return 回执信息
     */
    String requiredDeleteCompanyNature(String id);

    /**
     * 修改单位性质
     *
     * @param companyNature 单位性质实体类
     * @return 回执信息
     */
    String requiredUpdateCompanyNature(CompanyNature companyNature);

    /**
     * 查询所有单位性质
     *
     * @return json格式数据
     */
    String queryCompanyNature();
    
}
