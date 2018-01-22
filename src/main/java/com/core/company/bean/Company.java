package com.core.company.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by GSN on 2017/3/31.
 * 单位、学校、管理部门 、私人企业...
 */

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(generator = "uuid_company_id")
    @GenericGenerator(name = "uuid_company_id", strategy = "uuid")
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    /**
     * 上级单位
     * 0：最顶级单位
     */
    @Column(name = "p_id", nullable = false)
    private String pId;

    /**
     * 单位密匙
     * 1、用于授权用户为该单位管理员角色
     * 2、每次使用后将重新设置该密匙
     */
    @Column(name = "key_s", length = 28, nullable = false, unique = true)
    private String key;

    /**
     * 单位名称
     * 1、不能和审核通过的单位名称重复
     * 2、不能和等待审核的单位名称重复
     * 2、可以和其他单位的下级单位重复(pId != 0)
     */
    @Column(name = "name_", length = 100, nullable = false)
    private String name;

    /**
     * 单位类型
     */
    @Column(name = "type_id", nullable = false)
    private String typeId;

    /**
     * 单位性质
     */
    @Column(name = "nature_id", nullable = false)
    private String natureId;

    /**
     * 省市直辖区
     */
    @Column(name = "region_id", nullable = false)
    private String regionId;

    /**
     * 组织机构代码、社会信用代码
     * 1、不能和审核通过的单位代码重复
     * 2、不能和等待审核的单位代码重复
     */
    @Column(name = "code_")
    private String code;

    /**
     * 座机
     */
    @Column(name = "phone_", length = 24, nullable = false)
    private String phone;

    /**
     * 注册时间
     */
    @Column(name = "reg_date", nullable = false)
    private Date regTime;

    /**
     * 审核状态
     * 0：等待审核
     * 1：审核通过
     * 2：审核未通过
     * 注：修改单位信息变为等待审核状态
     */
    @Column(name = "check_")
    private int check;

    /**
     * 备注:审核不通过原因
     */
    @Column(name = "check_remark", length = 400)
    private String checkRemark;

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getNatureId() {
        return natureId;
    }

    public void setNatureId(String natureId) {
        this.natureId = natureId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public String getCheckRemark() {
        return checkRemark;
    }

    public void setCheckRemark(String checkRemark) {
        this.checkRemark = checkRemark;
    }
}
