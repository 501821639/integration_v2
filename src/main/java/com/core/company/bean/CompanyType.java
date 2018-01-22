package com.core.company.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by GSN on 2017/5/12.
 * 单位类型
 */

@Entity
@Table(name = "company_type")
public class CompanyType {

    @Id
    @GeneratedValue(generator = "uuid_company_type_id")
    @GenericGenerator(name = "uuid_company_type_id", strategy = "uuid")
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    /**
     * 类型名称
     */
    @Column(name = "name_", length = 80,nullable = false)
    private String name;

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
}
