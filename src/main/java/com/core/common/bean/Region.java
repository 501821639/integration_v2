package com.core.common.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by GSN on 2017/5/11.
 * 省市直辖区
 */

@Entity
@Table(name = "region")
public class Region {

    @Id
    @GeneratedValue(generator = "uuid_region_id")
    @GenericGenerator(name = "uuid_region_id", strategy = "uuid")
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    /**
     * 上级
     * 0：最高级节点
     */
    @Column(name = "p_id")
    private String pId;

    /**
     * 区域名
     */
    @Column(name = "name_", length = 40, nullable = false)
    private String name;

    /**
     * 邮编
     */
    @Column(name = "postcode_", length = 20)
    private String postcode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
