package com.core.common.assist;

import com.core.common.bean.Region;
import com.core.common.dao.RegionDao;
import com.utils.regular.NumberReg;

/**
 * Created by GSN on 2017/5/12.
 * 辅助Region类
 */
public class AssistRegion {

    private RegionDao regionDao;

    public AssistRegion(RegionDao regionDao) {
        this.regionDao = regionDao;
    }

    /**
     * 验证Region是否合法
     *
     * @param region 实体类
     * @return null数据合法
     */
    public String verificationRegion(Region region) {

        if (region == null) {
            return "Region null";
        }

        if (region.getpId() == null) {
            region.setpId("0");
        } else {

            region.setpId(region.getpId().trim());

            if (region.getpId().equals("") || region.getpId().equals("0")) {
                region.setpId("0");
            } else {

                if (regionDao.getRegion(region.getpId()) == null) {
                    return "上级节点不存在";
                }

            }

        }

        if (region.getName() == null) {
            return "请输入区域名称";
        } else {

            region.setName(region.getName().trim());

            if (region.getName().equals("")) {
                return "请输入区域名称";
            }

            if (region.getName().length() > 20) {
                return "区域名称长度限制20汉字、字符";
            }

        }

        if (region.getPostcode() != null) {

            region.setPostcode(region.getPostcode().replace(" ", ""));

            if (region.getPostcode().equals("")) {
                region.setPostcode(null);
            } else {
                if (!new NumberReg().number(region.getPostcode(), 6)) {
                    return "邮编格式错误(6位纯数字)";
                }
            }

        }

        return null;

    }

}
