package com.core.user.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by GSN on 2017/3/31.
 * 角色权限表
 */
@Entity
@Table(name = "role_permission", uniqueConstraints = {@UniqueConstraint(columnNames = {"role_id", "permission_id"})})
public class RolePermission implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid_role_permission_id")
    @GenericGenerator(name = "uuid_role_permission_id", strategy = "uuid")
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    //角色id
    @Column(name = "role_id")
    private String roleId;

    //权限id
    @Column(name = "permission_id")
    private String permissionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }
}
