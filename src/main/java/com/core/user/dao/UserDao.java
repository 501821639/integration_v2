package com.core.user.dao;


import com.core.user.bean.User;

import java.util.List;

/**
 * Created by GSN on 2017/4/26.
 * 暂无描述
 */
public interface UserDao {

    void addUser(User user);

    void updateUser(User user);

    User getUser(String id);

    List<?> queryUser(String hql);

}
