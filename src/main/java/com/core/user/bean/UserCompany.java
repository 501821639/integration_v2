package com.core.user.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by GSN on 2017/5/11.
 * 用户单位多对多关联表
 */

@Entity
@Table(name = "user_company", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "company_id"})})
public class UserCompany {

    @Id
    @GeneratedValue(generator = "uuid_user_company_id")
    @GenericGenerator(name = "uuid_user_company_id", strategy = "uuid")
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    //用户id
    @Column(name = "user_id")
    private String userId;

    //单位id
    @Column(name = "company_id")
    private String companyId;

    public UserCompany() {
    }

    public UserCompany(String userId, String companyId) {
        this.userId = userId;
        this.companyId = companyId;
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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
