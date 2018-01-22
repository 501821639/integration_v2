package com.core.common.service.impl;

import com.core.common.assist.AssistRegion;
import com.core.common.bean.Region;
import com.core.common.dao.RegionDao;
import com.core.common.service.RegionService;
import com.core.company.dao.CompanyDao;
import com.utils.json.FormatJson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by GSN on 2017/5/12.
 * 暂无描述
 */

@Service
public class RegionServiceImpl implements RegionService {

    @Resource
    private RegionDao regionDao;

    @Resource
    private CompanyDao companyDao;

    @Override
    public String requiredAddRegion(Region region) {

        String result = new AssistRegion(regionDao).verificationRegion(region);

        if (result != null) {
            return result;
        }

        regionDao.addRegion(region);

        return "success";
    }

    @Override
    public String requiredDeleteRegion(String id) {

        Region region = regionDao.getRegion(id);

        if (region == null) {
            return "ID error";
        }

        //是否存在下级节点
        String hql = "from Region r where r.pId='" + region.getId() + "'";

        if (!regionDao.queryRegion(hql).isEmpty()) {
            return "请先删除该节点所有下级节点";
        }

        //单位是否使用节点
        hql = "from Company c where c.regionId='" + region.getId() + "'";
        List<?> listCompany = companyDao.queryCompany(hql);

        if (!listCompany.isEmpty()) {
            return "该节点单位正在使用无法删除";
        }

        regionDao.deleteRegion(region);

        return "success";
    }

    @Override
    public String requiredUpdateRegion(Region region) {

        Region region1 = regionDao.getRegion(region.getId());

        if (region1 == null) {
            return "ID error";
        }

        String result = new AssistRegion(regionDao).verificationRegion(region);

        if (result != null) {
            return result;
        }

        region1.setpId(region.getpId());
        region1.setName(region.getName());
        region1.setPostcode(region.getPostcode());

        regionDao.updateRegion(region1);

        return "success";

    }

    @Override
    public String queryRegion() {

        List<?> list = regionDao.queryRegion("from Region");

        return new FormatJson().listConversion(list);
    }
}
