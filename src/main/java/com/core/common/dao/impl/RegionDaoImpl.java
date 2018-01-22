package com.core.common.dao.impl;

import com.core.common.bean.Region;
import com.core.common.dao.RegionDao;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by GSN on 2017/5/12.
 * 暂无描述
 */

@Repository
public class RegionDaoImpl implements RegionDao {

    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public void addRegion(Region region) {
        hibernateTemplate.save(region);
    }

    @Override
    public void deleteRegion(Region region) {
        hibernateTemplate.delete(region);
    }

    @Override
    public void deleteRegions(List<Region> list) {
        hibernateTemplate.deleteAll(list);
    }

    @Override
    public void updateRegion(Region region) {
        hibernateTemplate.update(region);
    }

    @Override
    public Region getRegion(String id) {
        return hibernateTemplate.get(Region.class, id);
    }

    @Override
    public List<?> queryRegion(String hql) {
        return hibernateTemplate.find(hql);
    }
}
