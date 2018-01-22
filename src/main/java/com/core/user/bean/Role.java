package com.core.user.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by GSN on 2017/3/31.
 * 角色表
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid_role_id")
    @GenericGenerator(name = "uuid_role_id", strategy = "uuid")
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    //角色名
    @Column(name = "name_", length = 40, nullable = false, unique = true)
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
