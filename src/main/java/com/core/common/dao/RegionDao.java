package com.core.common.dao;

import com.core.common.bean.Region;

import java.util.List;

/**
 * Created by GSN on 2017/5/12.
 * 暂无描述
 */
public interface RegionDao {

    void addRegion(Region region);

    void deleteRegion(Region region);

    void deleteRegions(List<Region> list);

    void updateRegion(Region region);

    Region getRegion(String id);

    List<?> queryRegion(String hql);

}
