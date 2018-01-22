package com.core.test.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by GSN on 2017/12/4.
 * 测试数据实体类（大数据千万数据模式）
 * 测试性能
 * 找出性能优化方式
 *
 * @author 郭少男
 */

@Entity
@Table(name = "test_one")
public class TestOneBean {

    @Id
    @GeneratedValue(generator = "uuid_user_id")
    @GenericGenerator(name = "uuid_user_id", strategy = "uuid")
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    /**
     * 测试数据用户名
     */
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    /**
     * 测试数据密码
     */
    @Column(name = "pwd", nullable = false)
    private String pwd;

    /**
     * 测试状态字段1
     */
    @Column(name = "num_one")
    private byte numOne;

    /**
     * 测试状态字段2
     */
    @Column(name = "num_two")
    private byte numTwo;

    /**
     * 测试状态字段3
     */
    @Column(name = "num_three")
    private byte numThree;

    /**
     * 测试状态字段4
     */
    @Column(name = "num_four")
    private byte numFour;

    /**
     * 测试状态字段5
     */
    @Column(name = "num_five")
    private byte numFive;

    /**
     * 时间
     */
    @Column(name = "time_", nullable = false)
    private Date time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public byte getNumOne() {
        return numOne;
    }

    public void setNumOne(byte numOne) {
        this.numOne = numOne;
    }

    public byte getNumTwo() {
        return numTwo;
    }

    public void setNumTwo(byte numTwo) {
        this.numTwo = numTwo;
    }

    public byte getNumThree() {
        return numThree;
    }

    public void setNumThree(byte numThree) {
        this.numThree = numThree;
    }

    public byte getNumFour() {
        return numFour;
    }

    public void setNumFour(byte numFour) {
        this.numFour = numFour;
    }

    public byte getNumFive() {
        return numFive;
    }

    public void setNumFive(byte numFive) {
        this.numFive = numFive;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
