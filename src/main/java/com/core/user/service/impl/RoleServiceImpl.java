package com.core.user.service.impl;

import com.core.user.bean.Role;
import com.core.user.dao.RoleDao;
import com.core.user.dao.UserRoleDao;
import com.core.user.service.RoleService;
import com.utils.json.FormatJson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by GSN on 2017/4/26.
 * 暂无描述
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Resource
    private UserRoleDao userRoleDao;

    @Override
    public String requiredAddRole(Role role) {

        if (role == null) {
            return "Role error";
        }

        if (role.getName() == null) {
            return "请填写角色名";
        }

        role.setName(role.getName().trim());

        if (role.getName().length() > 20) {
            return "角色名长度限制20汉字、字符";
        }

        String hql = "from Role r where r.name='" + role.getName() + "'";

        if (!roleDao.queryRole(hql).isEmpty()) {
            return "系统中已存在该角色";
        }

        roleDao.addRole(role);

        return "success";
    }

    @Override
    public String requiredDeleteRole(String id) {

        Role role = roleDao.getRole(id);

        if (role == null) {
            return "Id error";
        }

        String hql = "from UserRole ur where ur.roleId='" + role.getId() + "'";

        List<?> listUserRole = userRoleDao.queryUserRole(hql);

        if (!listUserRole.isEmpty()) {
            return "角色已被用户使用无法删除";
        }

        //删除角色
        roleDao.deleteRole(role);

        return "success";
    }

    @Override
    public String requiredUpdateRole(Role role) {

        if (role == null) {
            return "Role error";
        }

        Role dbRole = roleDao.getRole(role.getId());

        if (dbRole == null) {
            return "数据库中无匹配的该角色";
        }

        if (role.getName() == null) {
            return "请填写角色名";
        }

        role.setName(role.getName().trim());

        if (role.getName().length() > 20) {
            return "角色名长度限制20汉字、字符";
        }

        String hql = "from Role r where r.name='" + role.getName() + "'";

        if (!roleDao.queryRole(hql).isEmpty()) {
            return "系统中已存在该角色";
        }

        dbRole.setName(role.getName());

        roleDao.updateRole(dbRole);

        return "success";
    }

    @Override
    public String findRole() {
        String hql = "from Role";
        List<?> list = roleDao.queryRole(hql);
        return new FormatJson().listConversion(list);
    }
}
