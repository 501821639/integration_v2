package com.core.company.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by GSN on 2017/5/12.
 * 单位证明
 */

@Entity
@Table(name = "company_business_license")
public class CompanyBusinessLicense {


    @Id
    @GeneratedValue(generator = "uuid_cbl_id")
    @GenericGenerator(name = "uuid_cbl_id", strategy = "uuid")
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    /**
     * 单位id
     */
    @Column(name = "company_id",nullable = false)
    private String companyId;

    /**
     * 文件原名
     */
    @Column(name = "quondam_name",length = 50,nullable = false)
    private String quondamName;

    /**
     * 地址
     */
    @Column(name = "url_", nullable = false)
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getQuondamName() {
        return quondamName;
    }

    public void setQuondamName(String quondamName) {
        this.quondamName = quondamName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
