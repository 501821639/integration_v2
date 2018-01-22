package com.core.common.service;

import com.core.common.bean.Region;

/**
 * Created by GSN on 2017/5/12.
 * 暂无描述
 */
public interface RegionService {

    String requiredAddRegion(Region region);

    String requiredDeleteRegion(String id);

    String requiredUpdateRegion(Region region);

    String queryRegion();

}
