package com.core.company.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by GSN on 2017/5/15.
 * 单位性质
 */

@Entity
@Table(name = "company_nature")
public class CompanyNature {

    @Id
    @GeneratedValue(generator = "uuid_company_nature_id")
    @GenericGenerator(name = "uuid_company_nature_id", strategy = "uuid")
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    /**
     * 性质名称
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
