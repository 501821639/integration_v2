package com.core.common.controller;

import com.core.common.bean.Region;
import com.core.common.service.RegionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by GSN on 2017/5/12.
 * 暂无描述
 */

@Controller
@RequestMapping("/region")
public class RegionController {

    @Resource
    private RegionService regionService;

    @RequestMapping("/save.shtml")
    @RequiresPermissions("region:save")
    public void saveRegion(HttpServletResponse response, Region region) throws IOException {
        response.getWriter().print(regionService.requiredAddRegion(region));
    }

    @RequestMapping("/delete.shtml")
    @RequiresPermissions("region:delete")
    public void deleteRegion(HttpServletResponse response, String id) throws IOException {
        response.getWriter().print(regionService.requiredDeleteRegion(id));
    }

    @RequestMapping("/update.shtml")
    @RequiresPermissions("region:update")
    public void updateRegion(HttpServletResponse response, Region region) throws IOException {
        response.getWriter().print(regionService.requiredUpdateRegion(region));
    }

    @RequestMapping("/find.shtml")
    public void findRegion(HttpServletResponse response) throws IOException {
        response.getWriter().print(regionService.queryRegion());
    }

}
