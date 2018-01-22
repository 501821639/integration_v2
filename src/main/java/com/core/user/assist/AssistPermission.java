package com.core.user.assist;

import com.core.user.bean.Permission;

/**
 * Created by GSN on 2017/4/28.
 * 辅助Permission类
 */
public class AssistPermission {


    /**
     * 验证permission类中的属性是否合法
     *
     * @param permission 实体类引用对对象
     * @return null数据符合要求
     */
    public String verificationPermission(Permission permission) {

        if (permission == null) {
            return "permission null";
        }

        if (permission.getName() == null) {
            return "权限名称不能为空";
        }

        permission.setName(permission.getName().trim());

        if (permission.getName().equals("")) {
            return "权限名称不能为空";
        }

        if (permission.getName().length() > 20) {
            return "权限名长度限制20汉字、字符";
        }

        if (permission.getpId() == null || permission.getpId().trim() == "") {
            permission.setpId("0");
        }

        if (permission.getType() == null) {
            return "资源类型错误";
        }

        permission.setType(permission.getType().replace(" ", ""));

        if (!permission.getType().equals("menu") && !permission.getType().equals("permission")) {
            return "资源类型只能是menu与permission";
        }

        if (permission.getType().equals("menu")) {

            permission.setPermissionCode(null);

            if (permission.getControl() == null) {
                return "请填写URL地址";
            }

            permission.setControl(permission.getControl().replace(" ", ""));


            if (permission.getControl().equals("")) {
                return "请填写URL地址";
            }

            if (permission.getControl().length() > 200) {
                return "URL地址长度限制200字符";
            }

            if (permission.getIcon() != null) {
                permission.setIcon(permission.getIcon().trim());
                if (permission.getIcon().equals("")) {
                    permission.setIcon(null);
                } else {
                    if (permission.getIcon().length() > 60) {
                        return "菜单图标长度限制60字符";
                    }
                }
            }
        }

        if (permission.getType().equals("permission")) {

            permission.setControl(null);
            permission.setIcon(null);

            if (permission.getPermissionCode() == null) {
                return "请填写权限码";
            }

            permission.setPermissionCode(permission.getPermissionCode().trim());

            if (permission.getPermissionCode().equals("")) {
                return "请填写权限码";
            }

            if (permission.getPermissionCode().length() > 50) {
                return "权限码长度限制50字符";
            }

        }

        return null;

    }

}
