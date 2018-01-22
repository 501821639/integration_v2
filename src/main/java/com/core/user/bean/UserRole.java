package com.core.user.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by GSN on 2017/3/31.
 * 用户角色表
 */
@Entity
@Table(name = "user_role", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})})
public class UserRole implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid_user_role_id")
    @GenericGenerator(name = "uuid_user_role_id", strategy = "uuid")
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    //用户id
    @Column(name = "user_id")
    private String userId;

    //角色id
    @Column(name = "role_id")
    private String roleId;

    public UserRole() {
    }

    public UserRole(String userId, String roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
