package com.core.user.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by GSN on 2017/3/31.
 * 权限表
 */
@Entity
@Table(name = "permission")
public class Permission implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid_permission_id")
    @GenericGenerator(name = "uuid_permission_id", strategy = "uuid")
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    /**
     * 权限名
     */
    @Column(name = "name", length = 40, nullable = false, unique = true)
    private String name;

    /**
     * 二叉树节点
     * 0:最高节点
     */
    @Column(name = "pid", nullable = false)
    private String pId;

    /**
     * 排序
     * 从小到大
     */
    @Column(name = "order_")
    private int order;

    /**
     * 资源类型
     * menu : 菜单
     * permission : 权限
     */
    @Column(name = "type_", length = 40)
    private String type;

    /**
     * 权限码
     * 菜单：null
     * 权限：需要输入shiro对应的授权码
     */
    @Column(name = "permission_code", length = 100)
    private String permissionCode;

    /**
     * URL
     * 菜单：需填写对应的url页面
     * 权限：null
     */
    @Column(name = "control_", length = 400)
    private String control;

    /**
     * 菜单图标
     */
    @Column(name = "icon_", length = 120)
    private String icon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
